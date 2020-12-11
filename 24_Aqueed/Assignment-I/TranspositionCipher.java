import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class TranspositionCipher{
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the Original Text : ");
		String originalText = scanner.nextLine();
		String encryptedText = encryptText(originalText);
		System.out.println("\nEncrypted Text : " + encryptedText);
		String decryptedText = decryptText(encryptedText);
		System.out.print("\nDecrypted Text : " + decryptedText);
	}
	
	public static String encryptText(String originalText){
		String keyString = getKey();
		int rowSize = originalText.length()/keyString.length()+1;
		int colSize = keyString.length();
		char[][] matrix = new char[rowSize][colSize];
		int k=0;
		for(int i=0;i<rowSize;i++){
			for(int j=0;j<colSize;j++){
				if(k>=originalText.length()){
					matrix[i][j] = '$';
				}else{
					matrix[i][j] = originalText.charAt(k++);
				}
			}
		}
		
		List<Character> keyList = keyString.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
		StringBuilder encryptedText = new StringBuilder();
		for(int i=0;i<keyList.size();i++){
			int maxColIndex = keyList.indexOf(Collections.min(keyList));
			for(int j=0;j<rowSize;j++){
				encryptedText.append(matrix[j][maxColIndex]);
			}
			keyList.set(maxColIndex,'~');
		}
		return encryptedText.toString();
	}
	
	public static String decryptText(String encryptedText){
		String keyString = getKey();
		int rowSize = encryptedText.length()/keyString.length();
		int colSize = keyString.length();
		char[][] matrix = new char[rowSize][colSize];
		
		List<Character> keyList = keyString.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
		int encryptedTextPos=0;
		for(int i=0;i<keyList.size();i++){
			int maxColIndex = keyList.indexOf(Collections.min(keyList));
			for(int j=0;j<rowSize;j++){
				matrix[j][maxColIndex]=encryptedText.charAt(encryptedTextPos++);
			}
			keyList.set(maxColIndex,'~');
		}
		
		StringBuilder decryptedText = new StringBuilder();
		for(int i=0;i<rowSize;i++){
			for(int j=0;j<colSize;j++){
				if(matrix[i][j]=='$'){
					break;
				}else{
					decryptedText.append(matrix[i][j]);
				}
			}
		}
		
		
		return decryptedText.toString();
	}
	
	public static String getKey(){
		String key="";
		try{
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream("transKey.txt")));
			key = fileReader.readLine();
		}catch(FileNotFoundException fnfe){
			System.out.println(fnfe.getMessage());
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		
		return key;
	}
}