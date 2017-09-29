import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class gui extends JFrame {
	
	public static panel panel = new panel();
	
	public gui(){
		super("Scrabble");
		setSize(2150,2000);
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
		getContentPane().add(panel);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        //if (JOptionPane.showConfirmDialog(panel, "Are you sure to close this window?", "Really Closing?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	try {
						ScrabbleTester.TopTen.saveFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            System.exit(0);
		        }
		    //}
		});
	}
	
	
}
