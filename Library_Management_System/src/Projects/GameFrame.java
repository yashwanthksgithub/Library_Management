package Projects;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class GameFrame extends JFrame{

	GamePanelPing panel;
	
	public GameFrame(){
		panel = new GamePanelPing();
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	e.getWindow().dispose();
		        jframe.Additions home=new jframe.Additions();
		        home.setVisible(true);
		    }
		});
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}