package project;
import java.awt.EventQueue;

import java.awt.Font;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;

//import proj2;

import java.util.Random;
import javax.swing.JTextField;

class fun extends proj {    //class declaration
	int a;
	fun() { }
	public int pass1(String umove,String cmove) {
		return super.call(umove, cmove);
	}
	fun(int a) {
		this.a=a;
//		System.out.println(this.a);
	}
	
	void display1() {
//		System.out.println("Loading 1");
	}
}

class score extends fun {                                 //multilevel inheritance 
     private static int b=5;
	 score() {
		 super(b);                                 //super
	 }
	 @Override                                  //override
	 void display1() {
//			System.out.println("Loading 2");
		}
	public int pass(String umove,String cmove) {
		int a=super.pass1(umove, cmove);   
		proj2 p=new proj2();                             //import package
		p.right(a);
		return a;
	}
 }

class display {
	int a,b;
	display() {
	
	}
	display(int a,int b) {
		this.a=a;                   //this keyword
		this.b=b;
		//System.out.println(this.a+this.b);
	}
	void dj() {
//		System.out.println(this.a+this.b);
	}
	display dis(display obj) {
		this.a=obj.a;
		this.b=obj.b;
		return obj;
	}
}
interface inter {
	default void show() {                                                 //default
		JOptionPane.showMessageDialog(null, " Paper beats Rock ||"
				+ " Rock beats Scisser ||" + " Scisser beats Paper " );
	}
	int n=10;                         //variable
	void result(int v);            
}

class res implements inter {
	int a=10;
	public void result(int v) {
//		System.out.println("This is "+(v+n));
	}
}
public class GUI {

	String[] move= {"r","p","s"};
	public int u=0,c=0;
			int w=5; 
	 public JFrame frame;
	 JButton btnNewButton;
	 JButton btnNewButton_1;
	 JButton btnNewButton_2;
	score s = new score();
	fun f=new fun();
    int r;
     JTextField textField;
     private JTextField textField_1;
     
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {     //runnable 
			public void run() {
				try {
					
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 public GUI() {
		initialize();
		createEvent();
	}
	private void initialize() {
		frame = new JFrame();

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/project/th.jpg")));
		frame.getContentPane().setBackground(Color.GREEN);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("      Rock Paper Scissor");
		lblNewLabel.setFont(new Font("Times New Romans",Font.PLAIN,17));
		lblNewLabel.setForeground(Color.blue);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 31, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 106, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -130, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel);
		btnNewButton = new JButton();
		
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 50, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -40, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 133, SpringLayout.WEST, frame.getContentPane());
		btnNewButton.setIcon(new ImageIcon(GUI.class.getResource("/project/scissor.png")));
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton();
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 32, SpringLayout.EAST, btnNewButton);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_1, 0, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, 256, SpringLayout.WEST, frame.getContentPane());
		btnNewButton_1.setIcon(new ImageIcon(GUI.class.getResource("/project/paper.png")));
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton();
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_2, 31, SpringLayout.EAST, btnNewButton_1);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_2, 0, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_2, 122, SpringLayout.EAST, btnNewButton_1);
		btnNewButton_2.setIcon(new ImageIcon(GUI.class.getResource("/project/rock.png")));
		frame.getContentPane().add(btnNewButton_2);
		
		textField = new JTextField("Score- 0:0");
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -36, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 103, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, btnNewButton);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 0, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, -127, SpringLayout.EAST, btnNewButton_2);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, btnNewButton_2);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		frame.setBounds(200, 200, 449, 310);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		res r=new res();          //object declaration 
		r.show();
		display d=new display(5,6);           //passing parameters to constructors 
		d.dj();
		display di=new display();
		//display di2=new display();
		di=di.dis(d);                      //returning object
		di.dj();
		f.display1();
		s.display1();
	}
	
	private void createEvent() {
		
			btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(btnNewButton, "U choose Scissor");
				int y = new Random().nextInt(move.length);                          //nextInt
			      String cmove=move[y];
		        r=s.pass("s",cmove);	
		        win(r);
		        display(r);
		      
		        w--;
		        mark();
		        if(w==0)
		        	exit();
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(btnNewButton_1, "U choose Paper");
				int y = new Random().nextInt(move.length);
			      String cmove=move[y];
				r=s.pass("p",cmove);
				win(r);
				display(r);
				
				w--;
				mark();
				if(w==0)
					exit();
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(btnNewButton_2, "U choose Rock");
				int y = new Random().nextInt(move.length);
			      String cmove=move[y];
				r=s.pass("r",cmove);
				win(r);
				display(r);
				
				w--;
				mark();
				if(w==0)
					exit();
			}
		});
		}
	
private void display(int r) {
		if(r==1)
			textField_1.setText("1 point to you");
		else if(r==-1)
			textField_1.setText("1 point to computer");
		else
			textField_1.setText("Draw");
	}
public void win(int r)
{
	if(r==1)
		u++;
	else if(r==-1)
		c++;
	else if(r==0) 
		w++;
}	
public void mark() {
	textField.setText("Score- "+u+":"+c);
}
public void exit() {
	if(u>c) {
		JOptionPane.showMessageDialog(null, "You win");
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
	else {
		JOptionPane.showMessageDialog(null, "You lose");
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
//	JOptionPane.showMessageDialog(null, "Game Over");
	frame.setVisible(false); 
	frame.dispose();
}
}
