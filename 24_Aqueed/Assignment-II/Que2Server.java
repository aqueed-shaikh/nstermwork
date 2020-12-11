import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Que2Server {
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        ServerSocket serverSocket = new ServerSocket(6789);
        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String sentence = bufferedReader.readLine();
            String username = sentence.split(" ")[0];
            String password = sentence.split(" ")[1];
            String decryptedPassword = generateMessageDigest(decrypt(password));
            String response = "";
            if (username.equals("aqueed") && decryptedPassword.equals(generateMessageDigest("341998"))) {
                response = "Authentication Successful";
            } else {
                response = "Invalid Username or Password";
            }

            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            toClient.writeBytes(response + "\n");
        }
    }

    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String generateMessageDigest(String text) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        BigInteger bigInteger = new BigInteger(1, messageDigest.digest(text.getBytes()));
        String hexValue = bigInteger.toString(16);
        while (hexValue.length() < 32) {
            hexValue = "0" + hexValue;
        }
        return hexValue;
    }
}
