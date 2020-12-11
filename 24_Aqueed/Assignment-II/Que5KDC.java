import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Que5KDC {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 6789);
        while (true) {
            DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
            String sessionKey = "room2020";
            String receiverIdentity = "receiver2020";
            String senderIdentity = "sender2020";

            String firstPart = encryptReceiver(senderIdentity+"|"+receiverIdentity+"|"+sessionKey);
            String secondPart = encryptSender(sessionKey+"|"+firstPart);
            toServer.writeBytes(secondPart+"\n");
        }
    }

    public static String encryptReceiver(String value) {
        final String key = "aesEncryptionKey";
        final String initVector = "encryptionIntVec";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return new String(Base64.getEncoder().encode(encrypted));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String encryptSender(String value) {
        final String key = "aesEncryptionYek";
        final String initVector = "encryptionIntCev";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return new String(Base64.getEncoder().encode(encrypted));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
