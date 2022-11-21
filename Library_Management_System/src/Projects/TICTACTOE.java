package Projects;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class TICTACTOE implements ActionListener{
	
	Random random=new Random();
	JFrame frame=new JFrame();
	JPanel title_panel=new JPanel();
	JPanel button_panel=new JPanel();
	JLabel textfield=new JLabel();
	JButton[] button= new JButton[9];
	boolean player1_turn;
	
	public TICTACTOE(){
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
	    @Override
	    public void windowClosing(java.awt.event.WindowEvent e) {
	    	e.getWindow().dispose();
	        jframe.Additions home=new jframe.Additions();
	        home.setVisible(true);
	    }
	});
		frame.setSize(800,800);
		frame.getContentPane().setBackground(new Color(50,50,50));
		
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);

		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
		
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(150,150,150));
		
		
		for(int i=0;i<9;i++) {
			button[i]=new JButton();
			button_panel.add(button[i]);
			button[i].setFont(new Font("MV Boli",Font.BOLD,120));
			button[i].setFocusable(false);
			button[i].addActionListener(this);
		}
		
		
		title_panel.add(textfield);
		frame.add(title_panel,BorderLayout.NORTH);
		frame.add(button_panel);
		frame.setVisible(true);
	
		firstturn();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i=0;i<9;i++) {
			if(e.getSource()==button[i]) {
				if(player1_turn) {
					if(button[i].getText()=="") {
						button[i].setForeground(new Color(255,0,0));
						button[i].setText("X");
						player1_turn=false;
						textfield.setText("O  turn");
						check();
					}
				}
					else
					{
						if(button[i].getText()=="") {
							button[i].setForeground(new Color(0,0,255));
							button[i].setText("O");
							player1_turn=true;
							textfield.setText("X  turn");
							check();
						}
					}
				
			}
			
			
		}
		
		
		
	}
	
	public void firstturn() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(random.nextInt(2)==0) {
			player1_turn=true;
			textfield.setText("X turn");
		}
		else
		{
			player1_turn=false;
			textfield.setText("O turn");
		}
	}
	
	public void check() {
		if((button[0].getText()=="X")&&
		(button[1].getText()=="X")&&
		(button[2].getText()=="X"))
		{
			XWins(0,1,2);
		}
		if((button[3].getText()=="X")&&
				(button[4].getText()=="X")&&
				(button[5].getText()=="X"))
				{
					XWins(3,4,5);
				}
		if((button[6].getText()=="X")&&
				(button[7].getText()=="X")&&
				(button[8].getText()=="X"))
				{
					XWins(6,7,8);
				}
		if((button[0].getText()=="X")&&
				(button[3].getText()=="X")&&
				(button[6].getText()=="X"))
				{
					XWins(0,3,6);
				}
		if((button[1].getText()=="X")&&
				(button[4].getText()=="X")&&
				(button[7].getText()=="X"))
				{
					XWins(1,4,7);
				}
		if((button[2].getText()=="X")&&
				(button[5].getText()=="X")&&
				(button[8].getText()=="X"))
				{
					XWins(2,5,8);
				}
		if((button[0].getText()=="X")&&
				(button[4].getText()=="X")&&
				(button[8].getText()=="X"))
				{
				XWins(0,4,8);
				}
		if((button[2].getText()=="X")&&
				(button[4].getText()=="X")&&
				(button[6].getText()=="X"))
				{
				XWins(2,4,6);
				}
		
		//o wins
		if((button[0].getText()=="O")&&
				(button[1].getText()=="O")&&
				(button[2].getText()=="O"))
				{
					OWins(0,1,2);
				}
				if((button[3].getText()=="O")&&
						(button[4].getText()=="O")&&
						(button[5].getText()=="O"))
						{
							OWins(3,4,5);
						}
				if((button[6].getText()=="O")&&
						(button[7].getText()=="O")&&
						(button[8].getText()=="O"))
						{
							OWins(6,7,8);
						}
				if((button[0].getText()=="O")&&
						(button[3].getText()=="O")&&
						(button[6].getText()=="O"))
						{
							OWins(0,3,6);
						}
				if((button[1].getText()=="O")&&
						(button[4].getText()=="O")&&
						(button[7].getText()=="O"))
						{
							OWins(1,4,7);
						}
				if((button[2].getText()=="O")&&
						(button[5].getText()=="O")&&
						(button[8].getText()=="O"))
						{
							OWins(2,5,8);
						}
				if((button[0].getText()=="O")&&
						(button[4].getText()=="O")&&
						(button[8].getText()=="O"))
						{
						OWins(0,4,8);
						}
				if((button[2].getText()=="O")&&
						(button[4].getText()=="O")&&
						(button[6].getText()=="O"))
						{
						OWins(2,4,6);
						}
		
	}
	
	public void XWins(int a,int b,int c) {
		button[a].setBackground(Color.GREEN);
		button[b].setBackground(Color.GREEN);
		button[c].setBackground(Color.GREEN);

		for(int i=0;i<9;i++) {
			button[i].setEnabled(false);
		}
		textfield.setText("X won the Game");

		if(JOptionPane.showConfirmDialog(null, "Do You Want To play More Games", "Return",JOptionPane.YES_OPTION)==0) {
			frame.dispose();
			jframe.Additions home=new jframe.Additions();
			home.setVisible(true);
		}else {
			frame.dispose();
			jframe.HomePage home=new jframe.HomePage();
			home.setVisible(true);
		}
	}
	
	public void OWins(int a,int b,int c) {
		button[a].setBackground(Color.GREEN);
		button[b].setBackground(Color.GREEN);
		button[c].setBackground(Color.GREEN);

		for(int i=0;i<9;i++) {
			button[i].setEnabled(false);
		}
		textfield.setText("O won the Game");
		

		if(JOptionPane.showConfirmDialog(null, "Do You Want To play More Games", "Return",JOptionPane.YES_OPTION)==0) {
			frame.dispose();
			jframe.Additions home=new jframe.Additions();
			home.setVisible(true);
		}else {
			frame.dispose();
			jframe.HomePage home=new jframe.HomePage();
			home.setVisible(true);
		}
	}
	
	
}
