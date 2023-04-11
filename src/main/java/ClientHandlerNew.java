import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public class ClientHandlerNew extends WebSocketServer {

    private static final int portNumber = 4732;
    private static final ArrayList<User> users = new ArrayList<>();

    public ClientHandlerNew() {
        super(new InetSocketAddress(portNumber));
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        users.add(new User(webSocket));
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        User user = getUser(webSocket);
        users.remove(user);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {

        if(s.startsWith("name:")){
            String name = s.split(":")[1].trim();

            User user = getUser(webSocket);
            user.setName(name);
        }
        if(s.startsWith("link:")){
            String link = s.split(":")[1].trim();

            User user = getUser(webSocket);
            System.out.println(user.getName() + " linked " + link);
            user.setLink(link);
        }

        if (s.equalsIgnoreCase("dead")) {

            User user = getUser(webSocket);
            if(user != null) {
                String name = user.getName().trim();
                System.out.println(name + " died!");

                for (User user1 : users) {
                    if (user1.webSocket != null) {
                        if(user1.getLink() != null) {
                            if (user1.getLink().trim().equalsIgnoreCase(name)) {
                                System.out.println("Sent death to " + user1.getName());
                                user1.webSocket.send("dead");
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onStart() {
    }

    public User getUser(WebSocket socket){
        for(User user : users){
            if(user.webSocket.equals(socket)){
               return user;
            }
        }
        return null;
    }
}
