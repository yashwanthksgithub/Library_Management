package Projects;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jframe.Additions;
import jframe.HomePage;

public class SnakeFrame extends JFrame{
	
	public SnakeFrame(){
		this.add(new gamePanel());
		this.setTitle("Snake");
		
		
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	e.getWindow().dispose();
		        jframe.Additions home=new jframe.Additions();
		        home.setVisible(true);
		    }
		});
	}	
}
