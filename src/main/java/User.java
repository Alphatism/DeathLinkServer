import org.java_websocket.WebSocket;

public class User {

    public WebSocket webSocket;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String name;
    public String link;

    public User(WebSocket webSocket) {
        this.webSocket = webSocket;
    }




}
