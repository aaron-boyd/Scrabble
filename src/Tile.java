import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Path2D;

public class Tile {

	private String myletter;
	private int myvalue;
	private int myx, myy;
	private int myw, myh;
	private Color mycolor;
	private int mymult;
	private boolean myused;
	private boolean myword;

	public Tile() {
		myletter = "";
		myvalue = 0;
		myx = 0;
		myy = 0;
		myw = 0;
		myh = 0;
		myused = false;
		myword = false;
		mymult = 0;
	}

	public Tile(String letter, int value, int mult) {
		myletter = letter;
		myvalue = value;
		mymult = mult;
	}

	public Tile(Tile tile) {
		myletter = tile.getLetter();
		myvalue = tile.getValue();
		myx = tile.getX();
		myy = tile.getY();
		myw = tile.getW();
		myh = tile.getH();
		mycolor = tile.getColor();
		myused = tile.getUsed();
		myword = tile.getWord();
		mymult = tile.getMult();
	}

	public Tile(int x, int y, int w, int h) {
		myx = x;
		myy = y;
		myw = w;
		myh = h;
		myused = false;
	}

	public Tile(String letter, int value, int x, int y, int w, int h,int mult) {
		myletter = letter;
		myvalue = value;
		myx = x;
		myy = y;
		myw = w;
		myh = h;
		myused = false;
		mymult = mult;
	}

	public void setLetter(String letter) {
		myletter = letter;
	}

	public void setValue(int value) {
		myvalue = value;
	}

	public void setX(int x) {
		myx = x;
	}

	public void setY(int y) {
		myy = y;
	}

	public void setColor(Color color) {
		mycolor = color;
	}

	public void setUsed(boolean u) {
		myused = u;
	}

	public void setWord(boolean w) {
		myword = w;
	}
	
	public void setMult(int mult){
		mymult = mult;
	}

	public String getLetter() {
		return myletter;
	}

	public int getValue() {
		return myvalue;
	}

	public int getX() {
		return myx;
	}

	public int getY() {
		return myy;
	}

	public int getW() {
		return myw;
	}

	public int getH() {
		return myh;
	}

	public Color getColor() {
		return mycolor;
	}

	public boolean getUsed() {
		return myused;
	}

	public boolean getWord() {
		return myword;
	}
	
	public int getMult(){
		return mymult;
	}

	public String toString() {
		return (myletter + " " + myvalue);
	}

	public String cordtoString() {
		return ("(" + myx + "," + myy + ")");
	}

	public void drawTile(Graphics2D g) {
		g.setColor(mycolor);
		g.fillRect(myx, myy, myw, myh);
		if (myletter != "") {
			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font("Calibri", Font.PLAIN, 80));
			g.drawString(myletter, myx + 25, myy + 75);
			g.setFont(new Font("Calibri", Font.PLAIN, 40));
			g.drawString(Integer.toString(myvalue), myx + 75, myy + 95);
		}
		if(mymult == 1 && myletter.equals("")){
			g.setColor(new Color(255,255,255));
			g.setFont(new Font("Calibri",Font.PLAIN,28));
			g.drawString("Triple", myx + 15, myy + 40);
			g.drawString("Word", myx + 15, myy +70);
		}else if(mymult == 2 && myletter.equals("")){
			g.setColor(new Color(255,255,255));
			g.setFont(new Font("Calibri",Font.PLAIN,28));
			g.drawString("Double", myx + 10, myy + 40);
			g.drawString("Letter", myx + 15, myy +70);
		}else if(mymult == 3 && myletter.equals("")){
			g.setColor(new Color(255,255,255));
			g.setFont(new Font("Calibri",Font.PLAIN,28));
			g.drawString("Triple", myx + 15, myy + 40);
			g.drawString("Letter", myx + 15, myy +70);
		}else if(mymult == 4 && myletter.equals("")){
			g.setColor(new Color(255,255,255));
			g.setFont(new Font("Calibri",Font.PLAIN,28));
			g.drawString("Double", myx + 10, myy + 40);
			g.drawString("Word", myx + 15, myy +70);
		}else if(mymult == 5 && myletter.equals("")){
				g.setColor(new Color(0,0,0));
				g.fill(createStar(850, 850, 10, 30, 5, 55));
	}
		
	}
	
	private static Shape createStar(double centerX, double centerY,
	        double innerRadius, double outerRadius, int numRays,
	        double startAngleRad)
	    {
	        Path2D path = new Path2D.Double();
	        double deltaAngleRad = Math.PI / numRays;
	        for (int i = 0; i < numRays * 2; i++)
	        {
	            double angleRad = startAngleRad + i * deltaAngleRad;
	            double ca = Math.cos(angleRad);
	            double sa = Math.sin(angleRad);
	            double relX = ca;
	            double relY = sa;
	            if ((i & 1) == 0)
	            {
	                relX *= outerRadius;
	                relY *= outerRadius;
	            }
	            else
	            {
	                relX *= innerRadius;
	                relY *= innerRadius;
	            }
	            if (i == 0)
	            {
	                path.moveTo(centerX + relX, centerY + relY);
	            }
	            else
	            {
	                path.lineTo(centerX + relX, centerY + relY);
	            }
	        }
	        path.closePath();
	        return path;
	    }
	}

