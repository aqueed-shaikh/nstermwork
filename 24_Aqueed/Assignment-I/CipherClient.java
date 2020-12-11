import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CipherClient{
	public static void main(String argv[]) throws Exception{
		Scanner scanner = new Scanner(System.in);
		Socket clientSocket;

		while(true){
			System.out.print("Enter the Original Text : ");
			String originalText = scanner.nextLine();
			clientSocket = new Socket("127.0.0.1",6789);
			DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
			String encryptedText = encryptText(originalText);
			System.out.println("Encrypted Text : " + encryptedText);
			toServer.writeBytes(encryptedText + "\n");
		}
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