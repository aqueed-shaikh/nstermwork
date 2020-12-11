import java.io.*;
import java.net.*;

class CipherServer{
    public static void main(String[] argv) throws Exception{
        ServerSocket serverSocket = new ServerSocket(6789);
		String encryptedText;

        while(true){
            Socket connSocket = serverSocket.accept();
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
            encryptedText = clientInput.readLine();
			System.out.println("\nRecieved from Client : " + encryptedText);
			String decryptedText = decryptText(encryptedText);
			System.out.print("Decrypted Text : " + decryptedText); 
        }
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