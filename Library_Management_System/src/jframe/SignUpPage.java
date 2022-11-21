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
import rojerusan.RSButtonMetro;
public class SignUpPage extends JFrame {

	private JPanel contentPane;
	private JTextField txt_username;
	private JTextField txt_password;
	private JTextField txt_email;
	private JTextField txt_contact;
	private final Action action = new SwingAction();
	private JTextField txt_userid;

	/**
	 * Launch the application.
	 */
	
	public void inserSignupDetails() {
		String Id=txt_userid.getText();
		String name=txt_username.getText();
		String password=txt_password.getText();
		String email=txt_email.getText();
		String contact=txt_contact.getText();
		try {
			Connection con=DBConnection.getConnection();
		String sql="INSERT INTO user (name,password,email,contact) values(?,?,?,?)";
		PreparedStatement pat=con.prepareStatement(sql);	
//		pat.setInt(0, Id);
		pat.setString(1, name);
		pat.setString(2, password);
		pat.setString(3, email);
		pat.setString(4, contact);
		
		int updatedRowCount=pat.executeUpdate();
		if(updatedRowCount > 0) {
			JOptionPane.showMessageDialog(this, "Recorded Updated Successfully");
			LoginPage page=new LoginPage();
			page.setVisible(true);
			dispose(); 
		}else {
			JOptionPane.showMessageDialog(this, "failed To Update");

		}
		}
		catch(Exception e) {
			System.out.println("Error"+e);

		}
	}
	
	
	public boolean validateSignUp() {
		String id=txt_username.getText();
		String name=txt_username.getText();
		String password=txt_password.getText();
		String email=txt_email.getText();
		String contact=txt_contact.getText();
		if(id.equals("")) {
			JOptionPane.showMessageDialog(this, "Please Enter UserID");
			return false;
		}
		
		if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "Please Enter UserName");
			return false;
		}
		if(password.equals("")) {
			JOptionPane.showMessageDialog(this, "Please Enter New password");
			return false;
		}
		if(email.equals("")|| !email.matches("^.+@.+\\..+$")) {
			JOptionPane.showMessageDialog(this, "Please Enter vali Email_ID");
			return false;
		}
		if(contact.equals("")) {
			JOptionPane.showMessageDialog(this, "Please Enter Contact_Number");
			return false;
		}
		return true;
	}
	
	public boolean checkDuplicagteUser() {
		String name=txt_username.getText();
		String id=txt_userid.getText();
		
		boolean isExist=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","Yashu@123");
			PreparedStatement pst=con.prepareStatement("select * from admin where user_id = ? and name = ?");
			pst.setString(1, id);
			pst.setString(2, name);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				isExist=true;
			}else {
				isExist=false;
			}
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return isExist;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpPage frame = new SignUpPage();
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
	public SignUpPage() {
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
		panel.setBounds(0, 0, 999, 828);
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
		lblNewLabel_2.setBounds(326, 103, 351, 84);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(SignUpPage.class.getResource("/icons/signup-library-icon.png")));
		lblNewLabel_3.setBounds(101, 171, 815, 526);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(240, 255, 255));
		panel_1.setBackground(new Color(30, 144, 255));
		panel_1.setBounds(978, -28, 556, 856);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("PROFILE");
		lblNewLabel_1_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1_1.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_1_1.setBounds(181, 45, 208, 62);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Update Your Personal Account");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 0));
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 28));
		lblNewLabel_1_2.setBounds(86, 83, 407, 62);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("UserName");
		lblNewLabel_1_3.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3.setBounds(86, 280, 148, 31);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("");
		lblNewLabel_1_3_1.setIcon(new ImageIcon(SignUpPage.class.getResource("/icons/icons8_Account_50px.png")));
		lblNewLabel_1_3_1.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_1.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3_1.setBounds(25, 321, 60, 50);
		panel_1.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("New Password");
		lblNewLabel_1_3_3.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_3.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3_3.setBounds(86, 381, 208, 31);
		panel_1.add(lblNewLabel_1_3_3);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("");
		lblNewLabel_1_3_1_1.setIcon(new ImageIcon(SignUpPage.class.getResource("/icons/icons8_Secure_50px.png")));
		lblNewLabel_1_3_1_1.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_1_1.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3_1_1.setBounds(25, 422, 60, 50);
		panel_1.add(lblNewLabel_1_3_1_1);
		
		JLabel lblNewLabel_1_3_4 = new JLabel("Email-Id");
		lblNewLabel_1_3_4.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_4.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3_4.setBounds(86, 482, 148, 31);
		panel_1.add(lblNewLabel_1_3_4);
		
		JLabel lblNewLabel_1_3_1_2 = new JLabel("");
		lblNewLabel_1_3_1_2.setIcon(new ImageIcon(SignUpPage.class.getResource("/icons/icons8_Secured_Letter_50px.png")));
		lblNewLabel_1_3_1_2.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_1_2.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3_1_2.setBounds(25, 523, 60, 50);
		panel_1.add(lblNewLabel_1_3_1_2);
		
		JLabel lblNewLabel_1_3_5 = new JLabel("Contact");
		lblNewLabel_1_3_5.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_5.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3_5.setBounds(86, 583, 148, 31);
		panel_1.add(lblNewLabel_1_3_5);
		
		JLabel lblNewLabel_1_3_1_3 = new JLabel("");
		lblNewLabel_1_3_1_3.setIcon(new ImageIcon(SignUpPage.class.getResource("/icons/icons8_Google_Mobile_50px.png")));
		lblNewLabel_1_3_1_3.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_1_3.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3_1_3.setBounds(25, 624, 60, 50);
		panel_1.add(lblNewLabel_1_3_1_3);
		
		txt_username = new JTextField();
//		txt_username.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				if(checkDuplicagteUser()==true) {
//					JOptionPane.showMessageDialog(null,"Username Already exist");
//				}
//			}
//		});
		txt_username.setFont(new Font("Tahoma", Font.BOLD, 21));
		txt_username.setForeground(Color.BLACK);
		txt_username.setBackground(Color.WHITE);
		txt_username.setBounds(85, 321, 387, 50);
		panel_1.add(txt_username);
		txt_username.setColumns(10);
		
		txt_password = new JTextField();
		txt_password.setForeground(Color.BLACK);
		txt_password.setFont(new Font("Tahoma", Font.BOLD, 21));
		txt_password.setColumns(10);
		txt_password.setBackground(Color.WHITE);
		txt_password.setBounds(86, 422, 387, 50);
		panel_1.add(txt_password);
		
		txt_email = new JTextField();
		txt_email.setForeground(Color.BLACK);
		txt_email.setFont(new Font("Tahoma", Font.BOLD, 21));
		txt_email.setColumns(10);
		txt_email.setBackground(Color.WHITE);
		txt_email.setBounds(85, 523, 387, 50);
		panel_1.add(txt_email);
		
		txt_contact = new JTextField();
		txt_contact.setForeground(Color.BLACK);
		txt_contact.setFont(new Font("Tahoma", Font.BOLD, 21));
		txt_contact.setColumns(10);
		txt_contact.setBackground(Color.WHITE);
		txt_contact.setBounds(85, 624, 387, 50);
		panel_1.add(txt_contact);
		
		JButton btnNewButton = new JButton("Updated");
		btnNewButton.setAction(action);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
		btnNewButton.setForeground(new Color(0, 0, 153));
		btnNewButton.setBackground(new Color(255, 20, 147));
		btnNewButton.setBounds(114, 704, 347, 50);
		panel_1.add(btnNewButton);
		
		RSButtonMetro btnmtrBack = new RSButtonMetro();
		btnmtrBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage log=new LoginPage();
				log.setVisible(true);
				dispose();
			}
		});
		btnmtrBack.setBackground(Color.RED);
		btnmtrBack.setBounds(399, 781, 131, 38);
		panel_1.add(btnmtrBack);
		btnmtrBack.setFont(new Font("Tahoma", Font.BOLD, 23));
		btnmtrBack.setText("Back");
		
		JLabel lblNewLabel_1_3_2 = new JLabel("User Id");
		lblNewLabel_1_3_2.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_2.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3_2.setBounds(86, 179, 148, 31);
		panel_1.add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_3_1_4 = new JLabel("");
		lblNewLabel_1_3_1_4.setIcon(new ImageIcon(SignUpPage.class.getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png")));
		lblNewLabel_1_3_1_4.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_1_4.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3_1_4.setBounds(45, 220, 40, 50);
		panel_1.add(lblNewLabel_1_3_1_4);
		
		txt_userid = new JTextField();
		txt_userid.setForeground(Color.BLACK);
		txt_userid.setFont(new Font("Tahoma", Font.BOLD, 21));
		txt_userid.setColumns(10);
		txt_userid.setBackground(Color.WHITE);
		txt_userid.setBounds(85, 220, 387, 50);
		panel_1.add(txt_userid);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "UPDATE");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(validateSignUp()==true) {
				if(checkDuplicagteUser()==true) {
				inserSignupDetails();
				}else {
					JOptionPane.showMessageDialog(null,"User-ID Already Exist");
				}
			}
		}
	}
}
