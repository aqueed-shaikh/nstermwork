import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Que3Firewall {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6789);
        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String sentence = bufferedReader.readLine();
            System.out.println("\nSentence : " + sentence);

            if (sentence.contains("terrorist")) {
                System.out.println("\nCannot Forward Packet!");
            } else {
                Socket socket1 = new Socket("127.0.0.1", 1234);
                DataOutputStream toClient = new DataOutputStream(socket1.getOutputStream());
                toClient.writeBytes(sentence + "\n");
            }
        }
    }
}
