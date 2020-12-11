import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHA1Hash {
    public static void main(String args[]) throws NoSuchAlgorithmException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the String: ");
        String string = scanner.nextLine();
        System.out.println("Hash Value: " + convertToSHA(string));
    }
    public static String convertToSHA(String text) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        BigInteger bigInteger = new BigInteger(1, messageDigest.digest(text.getBytes()));
        String hexValue = bigInteger.toString(16);
        while (hexValue.length() < 32) {
            hexValue = "0" + hexValue;
        }
        return hexValue;
    }
}


