import java.io.IOException;

import javax.swing.JOptionPane;

public class ScrabbleTester {
	
	public static BagOfTiles bag = new BagOfTiles();
	public static Dictionary words = new Dictionary();
	public static gui gui = new gui();
	public static Player player = new Player();
	public static boolean isRunning;
	public static long time;
	public static topten TopTen = new topten();
	
	public static void main(String[] args) {
		isRunning = true;
		
		try {
			bag.openFile();
			words.openFile();
			TopTen.openFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BagOfTiles.shuffle();
		
	}
		
}
