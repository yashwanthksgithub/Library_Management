package jframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField txt_username;
	private JTextField txt_password;
	private final Action action = new SwingAction();
	

	/**
	 * Launch the application.
	 */
	
	public boolean validateLogin() {
		
		String name=txt_username.getText();
		String pwd=txt_password.getText();
		
		if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "Please Enter UserName");
			return false;
		}

		if(pwd.equals("")) {
			JOptionPane.showMessageDialog(this, "Please Enter Password");
			return false;
		}
		
		return true;
	}
	
	
	public void login() {
		String name=txt_username.getText();
		String pwd=txt_password.getText();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","Yashu@123");
			PreparedStatement pst=con.prepareStatement("select * from user where name=?  and password = ?");
			pst.setString(1, name);
			pst.setString(2, pwd);
			
			pst.executeQuery();
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				JOptionPane.showMessageDialog(this, "Login Successful");
				HomePage home=new HomePage();
				home.setVisible(true);
				this.dispose();
			
			}else {
				JOptionPane.showMessageDialog(this, "Incorrect Username or Password");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1523, 828);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(1523, 838));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 989, 838);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("YASHU");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.PINK);
		lblNewLabel.setBounds(0, 0, 69, 32);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("WELCOME TO ");
		lblNewLabel_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_1.setBounds(340, 59, 334, 62);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ADVANCE LIBRARY");
		lblNewLabel_2.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_2.setForeground(new Color(127, 255, 0));
		lblNewLabel_2.setBounds(340, 109, 351, 84);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(LoginPage.class.getResource("/icons/library-3.png.png")));
		lblNewLabel_3.setBounds(101, 171, 815, 526);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(240, 255, 255));
		panel_1.setBackground(Color.MAGENTA);
		panel_1.setBounds(976, 0, 556, 838);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("LOGIN PAGE");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1_1.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_1_1.setBounds(151, 36, 334, 62);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Login To Your Account");
		lblNewLabel_1_2.setForeground(Color.ORANGE);
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 31));
		lblNewLabel_1_2.setBounds(127, 91, 407, 62);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("UserName");
		lblNewLabel_1_3.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 27));
		lblNewLabel_1_3.setBounds(103, 191, 148, 31);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("");
		lblNewLabel_1_3_1.setIcon(new ImageIcon(SignUpPage.class.getResource("/icons/icons8_Account_50px.png")));
		lblNewLabel_1_3_1.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_1.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3_1.setBounds(38, 231, 60, 67);
		panel_1.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Password");
		lblNewLabel_1_3_3.setBackground(new Color(240, 240, 240));
		lblNewLabel_1_3_3.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_3.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 27));
		lblNewLabel_1_3_3.setBounds(103, 329, 148, 31);
		panel_1.add(lblNewLabel_1_3_3);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("");
		lblNewLabel_1_3_1_1.setIcon(new ImageIcon(SignUpPage.class.getResource("/icons/icons8_Secure_50px.png")));
		lblNewLabel_1_3_1_1.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_1_1.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3_1_1.setBounds(38, 386, 60, 50);
		panel_1.add(lblNewLabel_1_3_1_1);
		
		txt_username = new JTextField();
		
		txt_username.setFont(new Font("Tahoma", Font.BOLD, 21));
		txt_username.setForeground(Color.BLACK);
		txt_username.setBackground(Color.WHITE);
		txt_username.setBounds(103, 242, 369, 50);
		panel_1.add(txt_username);
		txt_username.setColumns(10);
		
		txt_password = new JTextField();
		txt_password.setForeground(Color.BLACK);
		txt_password.setFont(new Font("Tahoma", Font.BOLD, 21));
		txt_password.setColumns(10);
		txt_password.setBackground(Color.WHITE);
		txt_password.setBounds(103, 386, 369, 50);
		panel_1.add(txt_password);
		
		JButton btnNewButton = new JButton("Sign-UP");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				SignUpPage books=new SignUpPage();
				books.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
		btnNewButton.setForeground(new Color(0, 191, 255));
		btnNewButton.setBackground(new Color(255, 20, 147));
		btnNewButton.setBounds(116, 667, 347, 50);
		panel_1.add(btnNewButton);
		
		JButton btnSignin = new JButton("Login");
		btnSignin.setAction(action);
		btnSignin.setForeground(new Color(0, 0, 102));
		btnSignin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
		btnSignin.setBackground(new Color(255, 20, 147));
		btnSignin.setBounds(116, 513, 347, 50);
		panel_1.add(btnSignin);
		
		JLabel lblNewLabel_4 = new JLabel("If User Not Exist Create New Account");
		lblNewLabel_4.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 21));
		lblNewLabel_4.setBounds(103, 626, 382, 31);
		panel_1.add(lblNewLabel_4);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "LOGIN");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(validateLogin()) {
				login();
			}
		}
	}
}
