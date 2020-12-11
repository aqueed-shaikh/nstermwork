import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5Hash {
    public static void main(String args[]) throws NoSuchAlgorithmException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the String: ");
        String string = scanner.nextLine();
        System.out.println("Hash Value: " + convertToMD5(string));
    }

    public static String convertToMD5(String input)
    {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            byte[] messageDigest = mDigest.digest(input.getBytes());
            BigInteger bigInteger = new BigInteger(1, messageDigest);
            String hashValue = bigInteger.toString(16);
            while (hashValue.length() < 32) {
                hashValue = "0" + hashValue;
            }
            return hashValue;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

