import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Que4Receiver2 {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(3456);
        while(true){
            Socket socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String string = bufferedReader.readLine();
            System.out.println(string);
        }
    }
}

