import java.util.Scanner;

public class CaesarCipher{
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the Original Text : ");
		String originalText = scanner.nextLine();
		
		String encryptedText = encryptText(originalText);
		System.out.println("Encrypted Text : " + encryptedText);
		
		String decryptedText = decryptText(encryptedText);
		System.out.print("Decrypted Text : " + decryptedText); 
		
	}
	
	public static String decryptText(String encryptedText){
		StringBuilder decryptedText = new StringBuilder();
		
		for(int i=0;i<encryptedText.length();i++){
			if((encryptedText.charAt(i)>=65 && encryptedText.charAt(i)<=90)){
				decryptedText.append((char)((PositiveMod(encryptedText.charAt(i)-62,26)+65)));
			}else if((encryptedText.charAt(i)>=97 && encryptedText.charAt(i)<=122)){
				decryptedText.append((char)((PositiveMod(encryptedText.charAt(i)-94,26)+97)));
			}else if((encryptedText.charAt(i)>=48 && encryptedText.charAt(i)<=57)){
				decryptedText.append((char)((PositiveMod(encryptedText.charAt(i)-45,10)+48)));
			}else{
				decryptedText.append(encryptedText.charAt(i));
			}
		}
		
		return decryptedText.toString();
	}
	
	public static String encryptText(String originalText){
		StringBuilder encryptedText = new StringBuilder();
		
		for(int i=0;i<originalText.length();i++){
			if((originalText.charAt(i)>=65 && originalText.charAt(i)<=90)){
				encryptedText.append((char)((PositiveMod(originalText.charAt(i)-68,26)+65)));
			}else if((originalText.charAt(i)>=97 && originalText.charAt(i)<=122)){
				encryptedText.append((char)((PositiveMod(originalText.charAt(i)-100,26)+97)));
			}else if((originalText.charAt(i)>=48 && originalText.charAt(i)<=57)){
				encryptedText.append((char)((PositiveMod(originalText.charAt(i)-51,10)+48)));
			}else{
				encryptedText.append(originalText.charAt(i));
			}
		}
		
		return encryptedText.toString();
	}
	
	public static int PositiveMod(int value, int mod)
	{
		return ((value % mod + mod) % mod);
	}

}