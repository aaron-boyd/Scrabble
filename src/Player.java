import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Player {
	public String myname = "";
	public Tile[] myhand = new Tile[7];
	public int myscore = 0;
	public static int  myused = 0;
	public boolean me = false;

	public Player() {
		for (int i = 0; i < 7; i++) {
			myhand[i] = new Tile();
		}
		myscore = 0;
		myname = "";
		me = false;
	}
	
	public Player(Player player){
	 myname = player.myname;
	 myscore = player.myscore;
	 me = false;
	}
	
	public Player(String name, int score){
		myname = name;
		myscore = score;
		me = false;
	}

	public void deal() {
		for (int i = 0; i < 7; i++) {
			myhand[i] = new Tile(BagOfTiles.bag[myused].getLetter(), BagOfTiles.bag[myused].getValue(), (i * 200) + 200, 1750, 100, 100,0);
			myhand[i].setColor(new Color(228, 199, 164));
			myused++;
		}
		myscore= 0;
	}

	public void regdeal() {
		for (int i = 0; i < 7; i++) {
			if (myhand[i].getUsed() == true) {
				myhand[i] = new Tile(BagOfTiles.bag[myused].getLetter(), BagOfTiles.bag[myused].getValue(), (i * 200) + 200, 1750, 100, 100,0);
				myhand[i].setColor(new Color(228, 199, 164));
				myused++;
			}
		}
	}

	public void drawTray(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < 7; i++) {
			if (myhand[i].getUsed() == false) {
				g2.setFont(new Font("Calibri", Font.PLAIN, 80));
				g2.setColor(new Color(188, 159, 124));
				g2.fillRect((i * 200) + 200, 1750, 100, 110);
				g2.setColor(new Color(228, 199, 164));
				g2.fillRect((i * 200) + 200, 1750, 100, 100);
				g2.setColor(new Color(0, 0, 0));
				g2.drawString(ScrabbleTester.player.myhand[i].getLetter(), ((i + 1) * 200) + 25, 1825);
				g2.setFont(new Font("Calibri", Font.PLAIN, 40));
				g2.drawString(Integer.toString(ScrabbleTester.player.myhand[i].getValue()), ((i + 1) * 200) + 75, 1850);
			}
		}
	}
	public String toString(){
		return myname + "    " + myscore;
	}
	
	public void drawScore(Graphics2D g){
		g.setFont(new Font("Calibri",Font.PLAIN,72));
		g.drawString(toString(),1660,772);
	}
}
