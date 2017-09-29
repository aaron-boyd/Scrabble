import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class BagOfTiles {

	public static Tile[] newbag = new Tile[100];
	public static Tile[] bag = new Tile[100];
	public static Random rand = new Random();

	public void openFile() throws IOException {
		int[] arrnums = new int[100];
		int count = 0;
		Scanner reader = new Scanner(new File("Letters.txt"));

		while (reader.hasNext()) {

			String letter = reader.next();
			int val = Integer.parseInt(reader.next());
			int dis = Integer.parseInt(reader.next());
			
			for (int i = 0; i < dis; i++) {
				bag[count] = new Tile(letter, val,0);
				
				//System.out.println(bag[count].toString());
				count++;
			}
		}
		//System.out.println(count);
		reader.close();
	}
	
	public static void shuffle(){
		Tile temp = new Tile();
		int rnum = 0;
		for(int i = 0; i < 100; i++){
			rnum = rand.nextInt(100);
			temp = bag[i];
			bag[i] = bag[rnum];
			bag[rnum] = temp;
		}
		for(int i = 0; i < 100; i++){
			rnum = rand.nextInt(100);
			temp = bag[i];
			bag[i] = bag[rnum];
			bag[rnum] = temp;
		}
		for(int i = 0; i < 100; i++){
			rnum = rand.nextInt(100);
			temp = bag[i];
			bag[i] = bag[rnum];
			bag[rnum] = temp;
			//System.out.println(bag[i].toString());
		}
	}
}
