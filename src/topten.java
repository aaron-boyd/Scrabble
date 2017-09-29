import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class topten {
	
	public static Player[] tt = new Player[11];

	public void openFile() throws IOException{
		Scanner reader = new Scanner(new File("TopTen.txt"));
		int count = 0;
		
		while( reader.hasNext()){
			tt[count]= new Player(reader.next(), Integer.parseInt(reader.next()));
			//System.out.println(tt[count].toString());
			count++;
		}
		reader.close();
		tt[10] = new Player("",0);
		
	}
	
	public void saveFile() throws IOException{
		PrintWriter writer = new PrintWriter(new File("TopTen.txt"));
		System.out.println("Close");
		for(int i = 0; i < 10; i++){
			writer.println(tt[i].toString());
		}
		writer.close();
	}
	
	public void drawTop(Graphics2D g){
		Player temp = new Player();
		for(int i = 0; i <= 10; i++){
			if(tt[i].myname.equals(ScrabbleTester.player.myname) && tt[i].me == true){
				tt[i].myscore = ScrabbleTester.player.myscore;
			}
		}
		for(int front = 0; front < 10; front++){
			for(int back = front + 1; back <= 10; back++){
				if(tt[front].myscore < tt[back].myscore){
					temp = tt[front];
					tt[front] = tt[back];
					tt[back] = temp;
				}
			}
		}
		for(int i = 0; i <10; i ++){
			g.setFont(new Font("Calibri", Font.PLAIN, 48));
			//g.setColor(new Color(0,0,0));
			g.drawString((i+1) + ". " + tt[i].toString(), 1690,950+(i*48));
		}
	}
}
