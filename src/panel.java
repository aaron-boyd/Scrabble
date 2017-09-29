import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Font;
import javax.swing.plaf.FontUIResource;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class panel extends JPanel {

	public static Tile[] board = new Tile[225];
	public static Tile[] origboard = new Tile[225];
	public static Tile[] tray = new Tile[7];
	public static Tile start = new Tile();
	public static Tile endturn = new Tile();
	public static Tile clear = new Tile();
	public static int begin = 0;
	public static int selectednum = 8;
	public static int flag = 0;
	public static int[] used = new int[225];
	public static int tilecount = -1;
	public static String[] wordsused = new String[225];
	public static int wordcount = 0;
	public static int firstletter = 0;

	public panel() {
		createBoard();
		addMouseListener(new PanelListener());
		addMouseMotionListener(new PanelMotionListener());
		for (int i = 0; i < 225; i++) {
			wordsused[i] = new String("");
		}
	}

	public void paintComponent(Graphics g) {
		drawBoard(g);
	}

	public void drawBoard(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// DRAW BACK COLOR AND TRAY//
		g.setColor(new Color(128, 0, 0));
		g.fillRect(0, 0, 2500, 2000);
		g.setColor(new Color(148, 119, 84));
		g.fillRect(0, 1700, 1700, 300);
		g.setColor(new Color(148, 119, 84));
		g.fillRect(90, 90, 1520, 1520);

		// DRAW SQUARES//
		for (int i = 0; i <= 224; i++) {
			board[i].drawTile(g2);
		}

		// DRAW LINES//
		g2.setColor(new Color(0, 0, 0));
		for (int i = 1; i <= 16; i++) {
			g2.drawLine(100, 100 * i, 1600, 100 * i);
			g2.drawLine(i * 100, 100, i * 100, 1600);
		}

		// DRAW BUTTONS//
		g2.setColor(new Color(148, 119, 84));

		// DRAW START BACK//
		g2.fillRect(1700, 100, 310, 110);

		// DRAW END TURN BACK//
		g2.fillRect(1700, 300, 310, 110);

		// DRAW CLEAR BACK//
		g2.fillRect(1700, 500, 310, 110);

		// Player Stats Back
		g2.fillRect(1650, 700, 410, 110);

		// TopTen
		g2.fillRect(1650, 900, 410, 510);

		// DRAW START AND ENDTURN//
		g2.setColor(new Color(228, 199, 164));
		g2.fillRect(1700, 100, 300, 100);
		g2.fillRect(1700, 300, 300, 100);
		g2.fillRect(1700, 500, 300, 100);
		g2.fillRect(1650, 700, 400, 100);
		g2.fillRect(1650, 900, 400, 500);

		// DRAW START TEXT//
		g2.setColor(new Color(0, 0, 0));
		g2.setFont(new Font("Calibri", Font.BOLD, 72));
		g2.drawString("Start", 1772, 175);
		g2.drawString("End Turn", 1720, 375);
		g2.drawString("Clear", 1775, 575);
		// ASSIGN TILES//
		start = new Tile(1700, 100, 400, 100);
		endturn = new Tile(1700, 300, 400, 100);
		clear = new Tile(1700, 500, 400, 100);

		revalidate();

		if (selectednum != 8) {
			ScrabbleTester.player.myhand[selectednum].drawTile(g2);
		}
		if (begin == 2) {
			ScrabbleTester.player.drawTray(g2);
			ScrabbleTester.TopTen.drawTop(g2);
			// g2.drawString(Integer.toString(ScrabbleTester.player.myscore),
			// 1700, 900);
			ScrabbleTester.player.drawScore(g2);
		}

		if (begin == 1) {
			ScrabbleTester.player.deal();
			ScrabbleTester.player.drawTray(g2);
			ScrabbleTester.TopTen.drawTop(g2);
			// g2.drawString(Integer.toString(ScrabbleTester.player.myscore),
			// 1700, 900);
			ScrabbleTester.player.drawScore(g2);
			begin = 2;
		}

	}

	private class PanelListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			findObject(e);
			repaint();

		}

		public void mouseReleased(MouseEvent e) {
			int place = findBoardTile(e);
			int found = 0;
			// System.out.println(place);
			if (selectednum != 8 && place != 0) {
				if (board[place].getUsed() == false) {

					for (int i = 1; i < 14; i++) {
						// System.out.println(i + " " + (210 + i));
						if (place == i) {
							if (board[place + 1].getLetter() != "" || board[place - 1].getLetter() != "" || board[place + 15].getLetter() != "") {
								board[place].setLetter(ScrabbleTester.player.myhand[selectednum].getLetter());
								board[place].setValue(ScrabbleTester.player.myhand[selectednum].getValue());
								board[place].setColor(ScrabbleTester.player.myhand[selectednum].getColor());
								board[place].setUsed(true);
								ScrabbleTester.player.myhand[selectednum].setUsed(true);
								ScrabbleTester.player.myhand[selectednum].setX(4000);
								ScrabbleTester.player.myhand[selectednum].setY(4000);
								repaint();
								selectednum = 8;
								found = 1;
								break;
							}

						} else if (place == 210 + i) {
							if (board[place + 1].getLetter() != "" || board[place - 1].getLetter() != "" || board[place - 15].getLetter() != "") {
								board[place].setLetter(ScrabbleTester.player.myhand[selectednum].getLetter());
								board[place].setValue(ScrabbleTester.player.myhand[selectednum].getValue());
								board[place].setColor(ScrabbleTester.player.myhand[selectednum].getColor());
								board[place].setUsed(true);
								ScrabbleTester.player.myhand[selectednum].setUsed(true);
								ScrabbleTester.player.myhand[selectednum].setX(4000);
								ScrabbleTester.player.myhand[selectednum].setY(4000);
								repaint();
								selectednum = 8;
								found = 1;
								break;

							}
						}
					}
					if (found == 0) {
						if (board[place + 1].getLetter() != "" || board[place - 1].getLetter() != "" || board[place + 15].getLetter() != "" || board[place - 15].getLetter() != "") {
							board[place].setLetter(ScrabbleTester.player.myhand[selectednum].getLetter());
							board[place].setValue(ScrabbleTester.player.myhand[selectednum].getValue());
							board[place].setColor(ScrabbleTester.player.myhand[selectednum].getColor());
							board[place].setUsed(true);
							ScrabbleTester.player.myhand[selectednum].setUsed(true);
							ScrabbleTester.player.myhand[selectednum].setX(4000);
							ScrabbleTester.player.myhand[selectednum].setY(4000);
							repaint();
							selectednum = 8;

						} else {

							ScrabbleTester.player.myhand[selectednum].setX((selectednum * 200) + 200);
							ScrabbleTester.player.myhand[selectednum].setY(1750);
							ScrabbleTester.player.myhand[selectednum].setUsed(false);
							begin = 2;
							repaint();
							selectednum = 8;
							tilecount--;
						}

					}
				}
			}
		}
	}

	private class PanelMotionListener extends MouseMotionAdapter {

		public void mouseDragged(MouseEvent e) {
			if (flag == 1) {
				if (selectednum != 8) {
					ScrabbleTester.player.myhand[selectednum].setUsed(true);
					ScrabbleTester.player.myhand[selectednum].setX(e.getX());
					ScrabbleTester.player.myhand[selectednum].setY(e.getY());
					begin = 2;
					repaint();
				}
			}

		}
	}

	public void findObject(MouseEvent e) {

		for (int x = 0; x < 7; x++) {
			if (ScrabbleTester.player.myhand[x].getX() <= e.getX() && e.getX() <= ScrabbleTester.player.myhand[x].getX() + 100) {
				if (ScrabbleTester.player.myhand[x].getY() <= e.getY() && e.getY() <= ScrabbleTester.player.myhand[x].getY() + 100) {
					if (board[112].getLetter().equals("")) {
						ScrabbleTester.player.myhand[x].setUsed(true);
						board[112].setLetter(ScrabbleTester.player.myhand[x].getLetter());
						board[112].setValue(ScrabbleTester.player.myhand[x].getValue());
						board[112].setColor(ScrabbleTester.player.myhand[x].getColor());
						board[112].setUsed(true);
						// System.out.println(board[112].getLetter() + "\n"
						// +
						// board[112].getValue());
						tilecount = 0;
						used[tilecount] = 112;
						begin = 2;
						firstletter = 1;
						repaint();
					} else {
						selectednum = x;
						begin = 2;
						flag = 1;
						repaint();
					}
				}
			}
		}

		if (begin == 0) {
			if (start.getX() <= e.getX() && e.getX() <= start.getX() + start.getW()) {
				if (start.getY() <= e.getY() && e.getY() <= start.getY() + start.getH()) {
					begin = 1;

					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 45));
					UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 25));
					UIManager.put("OptionPane.inputdialogFont", new Font("System", Font.PLAIN, 50));

					ScrabbleTester.player.myname = JOptionPane.showInputDialog(this, "Please enter initials").toUpperCase();

					while (ScrabbleTester.player.myname.length() != 3) {
						ScrabbleTester.player.myname = JOptionPane.showInputDialog("Please enter only 3 initials").toUpperCase();
					}
					ScrabbleTester.TopTen.tt[10].myname = ScrabbleTester.player.myname;
					ScrabbleTester.TopTen.tt[10].myscore = ScrabbleTester.player.myscore;
					ScrabbleTester.TopTen.tt[10].me = true;
					repaint();
				}
			}
		}
		if (clear.getX() <= e.getX() && e.getX() <= clear.getX() + clear.getW()) {
			if (clear.getY() <= e.getY() && e.getY() <= clear.getY() + clear.getH()) {
				clear();
			}
		}

		if (endturn.getX() <= e.getX() && e.getX() <= endturn.getX() + endturn.getW()) {
			if (endturn.getY() <= e.getY() && e.getY() <= endturn.getY() + endturn.getH()) {
				calcPoints();
			}
		}
	}

	public void clear() {
		for (int i = 0; i <= tilecount; i++) {
			board[used[i]] = new Tile(origboard[used[i]]);
		}
		for (int i = 0; i < 7; i++) {
			if (ScrabbleTester.player.myhand[i].getUsed() == true) {
				ScrabbleTester.player.myhand[i].setUsed(false);
				ScrabbleTester.player.myhand[i].setX((i * 200) + 200);
				ScrabbleTester.player.myhand[i].setY(1750);
			}
		}
		tilecount = -1;
		repaint();
	}

	public int findBoardTile(MouseEvent e) {
		for (int i = 0; i <= 224; i++) {
			if (board[i].getX() <= e.getX() && e.getX() <= board[i].getX() + 100) {
				if (board[i].getY() <= e.getY() && e.getY() <= board[i].getY() + 100) {
					tilecount++;
					used[tilecount] = i;
					return i;
				}
			}
		}
		return 0;
	}

	public void createBoard() {
		int xoff = 1;
		int yoff = 0;
		int count = 0;
		int mult = 0;
		Color color = null;
		for (int i = 0; i < 225; i++) {
			if (i % 15 == 0) {
				yoff += 100;
				xoff = 1;
			}
			// TEST FOR COLOR
			if (count == 0 || count == 7 || count == 14 || count == 105 || count == 119 || count == 210 || count == 217 || count == 224) {
				// Orange//
				color = new Color(235, 128, 21);
				mult = 1;
			} else if (count == 3 || count == 11 || count == 36 || count == 38 || count == 45 || count == 52 || count == 59 || count == 92 || count == 96 || count == 98 || count == 102 || count == 108 || count == 116 || count == 122 || count == 126 || count == 128 || count == 132 || count == 165 || count == 172 || count == 179 || count == 186 || count == 188 || count == 213 || count == 221) {
				// Light Blue
				color = new Color(0, 176, 240);
				mult = 2;
			} else if (count == 20 || count == 24 || count == 76 || count == 80 || count == 84 || count == 88 || count == 136 || count == 140 || count == 144 || count == 148 || count == 200 || count == 204) {
				// Dark Blue
				color = new Color(58, 27, 215);
				mult = 3;
			} else if (count == 16 || count == 28 || count == 32 || count == 42 || count == 48 || count == 56 || count == 64 || count == 70 || count == 154 || count == 160 || count == 168 || count == 176 || count == 182 || count == 192 || count == 196 || count == 208) {
				// Pink//
				color = new Color(255, 52, 153);
				mult = 4;
			} else if (count == 112) {
				// Red//
				color = new Color(255, 50, 50);
				mult = 5;
			} else {
				// Tan//
				color = new Color(228, 199, 164);
				mult = 0;
			}

			board[i] = new Tile("", 0, (100 * xoff), yoff, 100, 100, mult);
			board[i].setColor(color);
			// System.out.println(i + " " + board[i].getMult());
			origboard[i] = new Tile("", 0, (100 * xoff), yoff, 100, 100, mult);
			origboard[i].setColor(color);
			count++;
			xoff++;
		}
	}

	public void calcPoints() {
		// System.out.println("call vert");
		VerticalWord();
		// System.out.println("call horz");
		HorizontalWord();

	}

	public void VerticalWord() {
		String test = "";
		Word word = new Word();
		boolean t = false;
		// Vertical//

		for (int i = 0; i < 15; i++) {
			for (int x = 0; x < 15; x++) {
				if (board[i + (x * 15)].getLetter() != "" && board[i + ((x - 1) * 15)].getLetter() == "") {
					test = vertTest(i + (x * 15));
					// System.out.println(test);
					if (test != null) {

						t = unique(test);

						if (t == true) {
							for (int q = 0; q <= test.length(); q++) {
								if (i + ((x + q) * 15) < 224 || i + ((x + q) * 15) > 0) {
									word.setTile(board[i + ((x + q) * 15)], q);
								}
							}
							System.out.println(word.toString());
							ScrabbleTester.player.myscore += word.findScore();
							ScrabbleTester.player.regdeal();
							for (int z = 0; z < 225; z++) {
								origboard[z].setColor(board[z].getColor());
								origboard[z].setLetter(board[z].getLetter());
								origboard[z].setValue(board[z].getValue());
							}
						}
					}
				}
			}
		}
		repaint();

	}

	public void HorizontalWord() {
		String test = "";
		Word word = new Word();
		boolean t = false;
		for (int i = 0; i < 15; i++) {
			for (int x = 0; x < 15; x++) {

				if (board[(i * 15) + x].getLetter() != "" && board[(i * 15) + x - 1].getLetter() == "") {
					test = horzTest((i * 15) + x);
					if (test != null) {
						t = unique(test);
						// System.out.println(test);

						if (t == true) {
							for (int q = 0; q < test.length(); q++) {
								word.setTile(board[(i * 15) + x + q], q);
							}
							ScrabbleTester.player.myscore += word.findScore();
							ScrabbleTester.player.regdeal();
							for (int z = 0; z < 225; z++) {
								origboard[z].setColor(board[z].getColor());
								origboard[z].setLetter(board[z].getLetter());
								origboard[z].setValue(board[z].getValue());
							}
							repaint();
						}
					}
				}
			}
		}
		clear();
	}

	public boolean unique(String word) {
		int p = 0;
		for (int i = 0; i <= wordcount; i++) {
			if (word.equals(wordsused[i])) {
				p = 1;
				break;
			}
		}
		// System.out.println("p = " + p);
		if (p == 0) {
			wordsused[wordcount] = word;
			// System.out.println("Words used: " + wordsused[wordcount]);
			// System.out.println("Word count: " + wordcount);
			// System.out.println(word + " is a unique word!\n");
			wordcount++;
			return true;
		} else {
			// System.out.println(word + " is not a unique word!\n");
			return false;
		}
	}

	public String vertTest(int index) {
		String word = board[index].getLetter();
		for (int i = 1; i <= 7; i++) {
			// System.out.println((i * 15));
			if (index + (i * 15) > 224 || index + (i * 15) < 0) {
				break;
			} else {
				if (board[index + (i * 15)].getLetter() != "") {

					word += board[index + (i * 15)].getLetter();

				} else if (board[index + (i * 15)].getLetter() == "") {

					break;
				}
			}
		}
		if (word.length() > 1) {
			word = Search(word);
			return word;
		} else {
			return null;
		}
	}

	public String horzTest(int index) {
		String word = board[index].getLetter();
		for (int i = 1; i <= 7; i++) {

			if (board[index + i].getLetter() != "") {

				word += board[index + i].getLetter();

			} else if (board[index + i].getLetter() == "") {

				break;
			}
		}
		// System.out.println(word);
		if (word.length() > 1) {
			word = Search(word);
			return word;
		} else {
			return null;
		}
	}

	public String Search(String word) {
		int high = 58112;
		int low = 0;
		int md = 0;
		int pos = 0;
		do {
			md = (high + low) / 2;
			if (word.compareTo(ScrabbleTester.words.arrwords[md]) < 0) {
				high = md - 1;
			} else if (word.compareTo(ScrabbleTester.words.arrwords[md]) > 0) {
				low = low + 1;
			} else {
				pos = md;
				break;
			}
		} while (low <= high);

		if (pos != 0) {
			// System.out.println("Word found");
			return word;
		} else {
			// System.out.println("Word not found");
			return null;
		}

	}

}
