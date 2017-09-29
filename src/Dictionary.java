import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Dictionary {

	public String[] arrwords = new String[58112];
	
	
	public void openFile() throws IOException{
		Scanner reader = new Scanner(new File("Dictionary.txt"));
		int count = 0;
		
		while(reader.hasNext()){
			arrwords[count] = reader.next();
			//System.out.println(arrwords[count]);
			count++;
		}
		reader.close();
	}
}
