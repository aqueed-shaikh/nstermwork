import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Que3Sender {
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("127.0.0.1", 6789);
        while(true){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
            System.out.print("Enter the String : ");
            String sentence = bufferedReader.readLine();
            toServer.writeBytes(sentence + "\n");
        }
    }
}
