package Projects;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import jframe.Additions;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class calculator {
	
	//private JTextField txtDisplay;
	public JFrame frame;
	private JTextField jtxtDisplay;
	
	//operands and operator
	double EnterNum1;
	double EnterNum2;
	double result;
	String operator;
	private final Action action = new SwingAction();//creating the objects of action 
	private final JLabel lblNewLabel = new JLabel("Back");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {//main class
		
				 
		EventQueue.invokeLater(new Runnable() {
			public void run() {//thread 
				try {//Exception handling
					calculator window = new calculator();//creating object of the calculator
					window.frame.setVisible(true);
				} catch (Exception e) {//catch of any type of exception 
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public calculator() {//constructor of same class name 
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {//private class which can be used in the same class
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBackground(new Color(0, 206, 209));//methods of inbuilt to design the frame
		frame.getContentPane().setBackground(new Color(32, 178, 170));
		frame.setBounds(100, 100, 369, 597);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		jtxtDisplay = new JTextField();//NEW->dynamically allocates a memory so,automatically deallocates memory ->garbage collector
		jtxtDisplay.setBounds(20, 0, 310, 83);
		jtxtDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		jtxtDisplay.setFont(new Font("Times New Roman", Font.BOLD, 27));
		frame.getContentPane().add(jtxtDisplay);
		jtxtDisplay.setColumns(10);
		
		
	//--------------> 1st row <-----------------------:
		
		JButton btnar = new JButton("\uF0E7");
		btnar.setBounds(20, 111, 60, 60);
		btnar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bs;
				if(jtxtDisplay.getText().length()>0)//string length function
				{
					StringBuilder stb=new StringBuilder(jtxtDisplay.getText());//object of class stringBuilder
					stb.deleteCharAt(jtxtDisplay.getText().length()-1);//character concept
					bs=stb.toString();//Conversion of char to string
					jtxtDisplay.setText(bs);//set display the function 
				}
				
			}
		});
		btnar.setBackground(new Color(255, 160, 122));
		btnar.setFont(new Font("Wingdings", Font.BOLD, 20));
		frame.getContentPane().add(btnar);
		
		JButton btnc = new JButton("C");
		btnc.setBounds(102, 111, 60, 60);
		btnc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//action performed by each button when we press
				
				jtxtDisplay.setText("");
			}
		});
		btnc.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnc.setBackground(new Color(255, 160, 122));
		frame.getContentPane().add(btnc);
		
		JButton btnper = new JButton("%");
		btnper.setBounds(189, 111, 60, 60);
		btnper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//same methods called many time so this called method overloading
				EnterNum1=Double.parseDouble(jtxtDisplay.getText());
				jtxtDisplay.setText(null);
				operator="%";
			}
		});
		btnper.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnper.setBackground(new Color(255, 160, 122));
		frame.getContentPane().add(btnper);
		
		JButton btnplus = new JButton("+");
		btnplus.setBounds(270, 111, 60, 60);
		btnplus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnterNum1=Double.parseDouble(jtxtDisplay.getText());
				jtxtDisplay.setText(null);
				operator="+";
			}
		});
		btnplus.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnplus.setBackground(new Color(255, 160, 122));
		frame.getContentPane().add(btnplus);
		
		final JButton btn8 = new JButton("8");
		btn8.setBounds(102, 202, 60, 60);
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String digit = jtxtDisplay.getText()+ btn8.getText();
				jtxtDisplay.setText(digit);
			}
		});
		btn8.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn8.setBackground(new Color(255, 160, 122));
		frame.getContentPane().add(btn8);
		
		final JButton btn9 = new JButton("9");
		btn9.setBounds(189, 202, 60, 60);
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String digit = jtxtDisplay.getText()+ btn9.getText();
				jtxtDisplay.setText(digit);
			}
		});
		btn9.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn9.setBackground(new Color(255, 160, 122));
		frame.getContentPane().add(btn9);
		
		JButton btnminus = new JButton("-");
		btnminus.setBounds(270, 202, 60, 60);
		btnminus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnterNum1=Double.parseDouble(jtxtDisplay.getText());
				jtxtDisplay.setText(null);
				operator="-";
			}
		});
		btnminus.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnminus.setBackground(new Color(255, 160, 122));
		frame.getContentPane().add(btnminus);
		
		
		//--------------> 3st row <-----------------------:
		
				final JButton btn4 = new JButton("4");
				btn4.setBounds(20, 292, 60, 60);
				btn4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String digit = jtxtDisplay.getText()+ btn4.getText();
						jtxtDisplay.setText(digit);
					}
				});
				btn4.setBackground(new Color(255, 160, 122));
				btn4.setFont(new Font("Tahoma", Font.BOLD, 20));
				frame.getContentPane().add(btn4);
				
				final JButton btn5 = new JButton("5");
				btn5.setBounds(102, 292, 60, 60);
				btn5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String digit = jtxtDisplay.getText()+ btn5.getText();
						jtxtDisplay.setText(digit);
					}
				});
				btn5.setFont(new Font("Tahoma", Font.BOLD, 20));
				btn5.setBackground(new Color(255, 160, 122));
				frame.getContentPane().add(btn5);
				
				final JButton btn6 = new JButton("6");
				btn6.setBounds(189, 292, 60, 60);
				btn6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String digit = jtxtDisplay.getText()+ btn6.getText();
						jtxtDisplay.setText(digit);
					}
					
				});
				btn6.setFont(new Font("Tahoma", Font.BOLD, 20));
				btn6.setBackground(new Color(255, 160, 122));
				frame.getContentPane().add(btn6);
				
				JButton btnmul = new JButton("*");
				btnmul.setBounds(270, 292, 60, 60);
				btnmul.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EnterNum1=Double.parseDouble(jtxtDisplay.getText());
						jtxtDisplay.setText(null);
						operator="*";
					}
				});
				btnmul.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnmul.setBackground(new Color(255, 160, 122));
				frame.getContentPane().add(btnmul);
				
				
				//--------------> 3st row <-----------------------:
				
				final JButton btn1 = new JButton("1");
				btn1.setBounds(20, 382, 60, 60);
				btn1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String digit = jtxtDisplay.getText()+ btn1.getText();
						jtxtDisplay.setText(digit);
					}
				});
				btn1.setBackground(new Color(255, 160, 122));
				btn1.setFont(new Font("Tahoma", Font.BOLD, 20));
				frame.getContentPane().add(btn1);
				
				final JButton btn2 = new JButton("2");
				btn2.setBounds(102, 382, 60, 60);
				btn2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String digit = jtxtDisplay.getText()+ btn2.getText();
						jtxtDisplay.setText(digit);
					}
				});
				btn2.setFont(new Font("Tahoma", Font.BOLD, 20));
				btn2.setBackground(new Color(255, 160, 122));
				frame.getContentPane().add(btn2);
				
				final JButton btn3 = new JButton("3");
				btn3.setBounds(189, 382, 60, 60);
				btn3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String digit = jtxtDisplay.getText()+ btn3.getText();
						jtxtDisplay.setText(digit);
					}
				});
				btn3.setFont(new Font("Tahoma", Font.BOLD, 20));
				btn3.setBackground(new Color(255, 160, 122));
				frame.getContentPane().add(btn3);
				
				JButton btnslash = new JButton("/");
				btnslash.setBounds(270, 382, 60, 60);
				btnslash.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EnterNum1=Double.parseDouble(jtxtDisplay.getText());
						jtxtDisplay.setText(null);
						operator="/";
					}
				});
				btnslash.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnslash.setBackground(new Color(255, 160, 122));
				frame.getContentPane().add(btnslash);
				
				
	//--------------> 4st row <-----------------------:
				
				final JButton btn0 = new JButton("0");
				btn0.setBounds(20, 467, 60, 60);
				btn0.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String digit = jtxtDisplay.getText()+ btn0.getText();
						jtxtDisplay.setText(digit);
					}
				});
				btn0.setBackground(new Color(255, 160, 122));
				btn0.setFont(new Font("Tahoma", Font.BOLD, 20));
				frame.getContentPane().add(btn0);
				
				final JButton btndot = new JButton(".");
				btndot.setBounds(102, 467, 60, 60);
				btndot.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!jtxtDisplay.getText().contains("."))
						{
							jtxtDisplay.setText(jtxtDisplay.getText()+ btndot.getText());
						}
					}
				});
				btndot.setFont(new Font("Tahoma", Font.BOLD, 20));
				btndot.setBackground(new Color(255, 160, 122));
				frame.getContentPane().add(btndot);
				
				JButton btnpm = new JButton("+-");
				btnpm.setBounds(189, 467, 60, 60);
				btnpm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						double pm=Double.parseDouble(String.valueOf(jtxtDisplay.getText()));
						pm=pm*(-1);
						jtxtDisplay.setText(String.valueOf(pm));
					}
					
				});
				btnpm.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnpm.setBackground(new Color(255, 160, 122));
				frame.getContentPane().add(btnpm);
				
				JButton btneq = new JButton("=");//if-else operation in oopJava
				btneq.setBounds(270, 467, 60, 60);
				btneq.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EnterNum2=Double.parseDouble(jtxtDisplay.getText());
						if(operator=="+")
						{
							result=EnterNum1+EnterNum2;
							jtxtDisplay.setText(String.valueOf(result));
						}
						else if(operator=="-")
						{
							result=EnterNum1-EnterNum2;
							jtxtDisplay.setText(String.valueOf(result));
						}
						else if(operator=="*")
						{
							result=EnterNum1*EnterNum2;
							jtxtDisplay.setText(String.valueOf(result));
						}
						else if(operator=="/")
						{
							result=EnterNum1/EnterNum2;
							jtxtDisplay.setText(String.valueOf(result));
						}
						else if(operator=="%")
						{
							result=EnterNum1%EnterNum2;
							jtxtDisplay.setText(String.valueOf(result));
						}
					}
				});
				btneq.setFont(new Font("Tahoma", Font.BOLD, 20));
				btneq.setBackground(new Color(255, 160, 122));
				frame.getContentPane().add(btneq);
				
				final JButton btn7 = new JButton("7");
				btn7.setBounds(20, 203, 60, 60);
				btn7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String digit = jtxtDisplay.getText()+ btn7.getText();
						jtxtDisplay.setText(digit);
					}
				});
				btn7.setFont(new Font("Tahoma", Font.BOLD, 18));
				btn7.setBackground(new Color(255, 160, 122));
				frame.getContentPane().add(btn7);
				
				JButton btnNewButton = new JButton("YASHU");
				btnNewButton.setBounds(260, 537, 85, 21);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnNewButton.setForeground(new Color(248, 248, 255));
				btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 12));
				btnNewButton.setBackground(new Color(255, 69, 0));
				frame.getContentPane().add(btnNewButton);
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(255, 51, 102));
				panel.setBounds(0, 559, 114, 38);
				frame.getContentPane().add(panel);
				panel.setLayout(null);
				lblNewLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Additions game=new Additions();
						game.setVisible(true);
						frame.dispose();
					}

				});
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
				lblNewLabel.setIcon(new ImageIcon(calculator.class.getResource("/AddNewBookIcons/icons8_Rewind_48px.png")));
				lblNewLabel.setBounds(0, 0, 114, 36);
				panel.add(lblNewLabel);
	}
	private class SwingAction extends AbstractAction {//inheritance concept
		public SwingAction() {//constructor 
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {//calling same method name in different class ->Method Overriding
		}
	}
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
