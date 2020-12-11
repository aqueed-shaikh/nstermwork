import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Que4Nat {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6789);
        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String sentence = bufferedReader.readLine();
            System.out.println("\nSentence : " + sentence);

            String user = sentence.split(" ")[0];
            String message = sentence.split(" ")[1];
            int port;

            if (user.equals("receiver1")) {
                port = 1234;
            } else {
                port = 3456;
            }

            Socket socket1 = new Socket("127.0.0.1", port);
            DataOutputStream toClient = new DataOutputStream(socket1.getOutputStream());
            toClient.writeBytes(message+"\n");
        }
    }
}

