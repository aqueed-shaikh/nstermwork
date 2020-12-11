import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Que5Sender{
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(6789);
        while(true){
            Socket socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String sentence = bufferedReader.readLine();
            System.out.println("\nSentence : " + sentence);

            String decryptedString = decrypt(sentence);
            System.out.println(decryptedString);

            Socket socket1 = new Socket("127.0.0.1", 1234);
            DataOutputStream toClient = new DataOutputStream(socket1.getOutputStream());
            toClient.writeBytes(decryptedString.split("|")[1]+"\n");
        }
    }

    public static String decrypt(String encrypted) {
        final String key = "aesEncryptionYek";
        final String initVector = "encryptionIntCev";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
