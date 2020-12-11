import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Scanner;

public class DESAlgorithm {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        String keyString = "Aqueed34";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Original String : ");
        String originalText = scanner.nextLine();
        DESKeySpec desKeySpec = new DESKeySpec(keyString.getBytes());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES");
        String encryptedText = encryptText(originalText,cipher,secretKey);
        System.out.println("Encrypted String: " + encryptedText);
        String decryptedText = decryptText(encryptedText,cipher,secretKey);
        System.out.println("Decrypted String: " + decryptedText);
    }

    public static String encryptText(String originalText,Cipher cipher,SecretKey secretKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        byte[] originalByteArray = originalText.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedByteArray = cipher.doFinal(originalByteArray);
        byte[] encodedByteArray = Base64.getEncoder().encode(encryptedByteArray);
        return new String(encodedByteArray);
    }

    public static String decryptText(String encryptedText,Cipher cipher,SecretKey secretKey) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        byte[] encryptedByteArray = encryptedText.getBytes();
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedByteArray);
        byte[] decryptedByteArray = cipher.doFinal(decodedBytes);
        return new String(decryptedByteArray, StandardCharsets.UTF_8);
    }
}