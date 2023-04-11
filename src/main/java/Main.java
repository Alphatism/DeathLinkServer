import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            ClientHandlerNew clientHandlerNew = new ClientHandlerNew();
            clientHandlerNew.start();

            BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String in = systemIn.readLine();
                if (in.equals("exit")) {
                    clientHandlerNew.stop(1000);
                    break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}