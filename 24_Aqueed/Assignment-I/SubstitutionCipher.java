import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SubstitutionCipher{
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
		int key = getKey();
		
		for(int i=0;i<encryptedText.length();i++){
			if((encryptedText.charAt(i)>=65 && encryptedText.charAt(i)<=90)){
				decryptedText.append((char)((PositiveMod(encryptedText.charAt(i)-(65+key),26)+65)));
			}else if((encryptedText.charAt(i)>=97 && encryptedText.charAt(i)<=122)){
				decryptedText.append((char)((PositiveMod(encryptedText.charAt(i)-(97+key),26)+97)));
			}else if((encryptedText.charAt(i)>=48 && encryptedText.charAt(i)<=57)){
				decryptedText.append((char)((PositiveMod(encryptedText.charAt(i)-(48+key),10)+48)));
			}else{
				decryptedText.append(encryptedText.charAt(i));
			}
		}
		
		return decryptedText.toString();
	}
	
	public static String encryptText(String originalText){
		StringBuilder encryptedText = new StringBuilder();
		int key = getKey();
		
		for(int i=0;i<originalText.length();i++){
			if((originalText.charAt(i)>=65 && originalText.charAt(i)<=90)){
				encryptedText.append((char)((PositiveMod(originalText.charAt(i)-(65-key),26)+65)));
			}else if((originalText.charAt(i)>=97 && originalText.charAt(i)<=122)){
				encryptedText.append((char)((PositiveMod(originalText.charAt(i)-(97-key),26)+97)));
			}else if((originalText.charAt(i)>=48 && originalText.charAt(i)<=57)){
				encryptedText.append((char)((PositiveMod(originalText.charAt(i)-(48-key),10)+48)));
			}else{
				encryptedText.append(originalText.charAt(i));
			}
		}
		
		return encryptedText.toString();
	}
	
	public static int getKey(){
		int key=-1;
		try{
			FileInputStream fileInputStream = new FileInputStream("keyFile.txt");
			key = (int)fileInputStream.read()-48;
		}catch(FileNotFoundException fnfe){
			System.out.println(fnfe.getMessage());
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		return key;
	}
	
	public static int PositiveMod(int value, int mod)
	{
		return ((value % mod + mod) % mod);
	}

}