package jframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
import rojeru_san.complementos.RSTableMetro;
import javax.swing.JScrollPane;
import rojerusan.RSMaterialButtonRectangle;
import java.awt.event.ActionListener;
public class AdminPage extends JFrame {

	private JPanel contentPane;
	private JTextField txt_username;
	private JTextField txt_password;
	private final Action action = new SwingAction();
	

	/**
	 * Launch the application.
	 */
	RSTableMetro tableMetro;
	
	public boolean validateLogin() {
		
		String ID=txt_username.getText();
		String pwd=txt_password.getText();
		
		if(ID.equals("")) {
			JOptionPane.showMessageDialog(this, "Please Enter USER_ID");
			return false;
		}

		if(pwd.equals("")) {
			JOptionPane.showMessageDialog(this, "Please Enter USER NAME");
			return false;
		}
		
		return true;
	}
	
	int ID;
	public boolean login() {
		boolean added=false;
//		String ID=txt_username.getText();
		 ID=Integer.parseInt(txt_username.getText());
		String Name=txt_password.getText();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","Yashu@123");
		    String sql="insert into admin values(?,?)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, ID);
			pst.setString(2, Name);
			int rowCount=pst.executeUpdate();
			 
			if(rowCount>0) {
				added=true;
				LoginPage home=new LoginPage();
				home.setVisible(true);
				this.dispose();
				
			 }else {
				 JOptionPane.showMessageDialog(this, "Incorrect User ID or Name");
				 added=false;
			 }
			 return added;
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 return added;
	 }
			
	 public void setbookdetalis() {
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","Yashu@123");
				Statement sr=con.createStatement();
				String query="select * from admin";
				ResultSet rs=sr.executeQuery(query);
				
				ResultSetMetaData rsmd=rs.getMetaData();
				
				DefaultTableModel model = (DefaultTableModel)tableMetro.getModel();
				
				int cols=rsmd.getColumnCount();
				String[] colName=new String[cols];
				for(int i=0;i<cols;i++) {
					colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
				}
				String id,name;
				
				while(rs.next()) {
					id=rs.getString(1);
					name=rs.getString(2);
					String[] row= {id,name};
					model.addRow(row);
				}
				sr.close();
				con.close();
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
	 }
	 
	 public void clearTable() {
			DefaultTableModel model = (DefaultTableModel)tableMetro.getModel();
			model.setRowCount(0);
	 }
	 
	 
	 public boolean deletebook() {
		 boolean delete=false;
		 ID=Integer.parseInt(txt_username.getText());
		 try {
			 Connection con=DBConnection.getConnection();
			 String sql="delete from admin where user_id = ?";
			 PreparedStatement pt=con.prepareStatement(sql);
			 pt.setInt(1, ID);
			 int rowCount=pt.executeUpdate();
			 if(rowCount>0) {
				 delete=true;
			 }else {
				 delete=false;
			 }
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 return delete;
	 }
	 
	 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
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
	public AdminPage() {
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
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(AdminPage.class.getResource("/icons/admin.jpg")));
		lblNewLabel_3.setBounds(44, 132, 551, 617);
		panel.add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(637, 37, 293, 546);
		panel.add(scrollPane);
		
		tableMetro = new RSTableMetro();
		tableMetro.setForeground(new Color(0, 0, 255));
		tableMetro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableMetro.setColorBackgoundHead(new Color(255, 102, 204));
		tableMetro.setColorFilasBackgound2(Color.WHITE);
		
		
		tableMetro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int rowNo=tableMetro.getSelectedRow();
				TableModel model=tableMetro.getModel();
				
				txt_username.setText(model.getValueAt(rowNo, 0).toString());
				txt_password.setText(model.getValueAt(rowNo, 1).toString());
			}
		});
		
		
		scrollPane.setViewportView(tableMetro);
		
		RSMaterialButtonRectangle mtrlbtnrctnglView = new RSMaterialButtonRectangle();
		mtrlbtnrctnglView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				setbookdetalis();
			}
		});
		mtrlbtnrctnglView.setForeground(new Color(255, 0, 204));
		mtrlbtnrctnglView.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 30));
		mtrlbtnrctnglView.setBackground(new Color(102, 153, 255));
		mtrlbtnrctnglView.setText("View");
		mtrlbtnrctnglView.setBounds(657, 598, 252, 70);
		panel.add(mtrlbtnrctnglView);
		
		JLabel lblNewLabel_1 = new JLabel("WELCOME TO ");
		lblNewLabel_1.setBounds(131, 30, 334, 62);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 40));
		
		JLabel lblNewLabel_2 = new JLabel("ADVANCE LIBRARY");
		lblNewLabel_2.setBounds(131, 71, 351, 84);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_2.setForeground(new Color(127, 255, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(240, 255, 255));
		panel_1.setBackground(Color.MAGENTA);
		panel_1.setBounds(976, 0, 556, 838);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("ADMIN PAGE");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1_1.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_1_1.setBounds(154, 75, 334, 62);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Create User ");
		lblNewLabel_1_2.setForeground(new Color(51, 204, 255));
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 31));
		lblNewLabel_1_2.setBounds(210, 132, 187, 62);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("User ID");
		lblNewLabel_1_3.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 27));
		lblNewLabel_1_3.setBounds(104, 242, 148, 31);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("");
		lblNewLabel_1_3_1.setIcon(new ImageIcon(SignUpPage.class.getResource("/icons/icons8_Account_50px.png")));
		lblNewLabel_1_3_1.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_1.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3_1.setBounds(33, 414, 60, 67);
		panel_1.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("User Name");
		lblNewLabel_1_3_3.setBackground(new Color(240, 240, 240));
		lblNewLabel_1_3_3.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_3.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 27));
		lblNewLabel_1_3_3.setBounds(103, 389, 200, 31);
		panel_1.add(lblNewLabel_1_3_3);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("");
		lblNewLabel_1_3_1_1.setIcon(new ImageIcon(SignUpPage.class.getResource("/icons/icons8_Secure_50px.png")));
		lblNewLabel_1_3_1_1.setForeground(new Color(240, 255, 255));
		lblNewLabel_1_3_1_1.setFont(new Font("STZhongsong", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1_3_1_1.setBounds(38, 283, 60, 50);
		panel_1.add(lblNewLabel_1_3_1_1);
		
		txt_username = new JTextField();
		
		txt_username.setFont(new Font("Tahoma", Font.BOLD, 21));
		txt_username.setForeground(Color.BLACK);
		txt_username.setBackground(Color.WHITE);
		txt_username.setBounds(103, 283, 369, 50);
		panel_1.add(txt_username);
		txt_username.setColumns(10);
		
		txt_password = new JTextField();
		txt_password.setForeground(Color.BLACK);
		txt_password.setFont(new Font("Tahoma", Font.BOLD, 21));
		txt_password.setColumns(10);
		txt_password.setBackground(Color.WHITE);
		txt_password.setBounds(103, 430, 369, 50);
		panel_1.add(txt_password);
		
		JButton btnSignin = new JButton("Create");
		
		
		btnSignin.setAction(action);
		btnSignin.setForeground(new Color(0, 0, 102));
		btnSignin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
		btnSignin.setBackground(new Color(255, 20, 147));
		btnSignin.setBounds(104, 551, 347, 50);
		panel_1.add(btnSignin);
		
		RSMaterialButtonRectangle mtrlbtnrctnglDelete = new RSMaterialButtonRectangle();
		mtrlbtnrctnglDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(deletebook()==true) {
					clearTable();
					setbookdetalis();
					JOptionPane.showMessageDialog(null, "USER Deleted ");
				}
				else {
					JOptionPane.showMessageDialog(null, "Failed To Delete A User");

				}
			}
		});
		mtrlbtnrctnglDelete.setText("Delete");
		mtrlbtnrctnglDelete.setForeground(new Color(0, 0, 0));
		mtrlbtnrctnglDelete.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 30));
		mtrlbtnrctnglDelete.setBackground(Color.RED);
		mtrlbtnrctnglDelete.setBounds(104, 641, 334, 56);
		panel_1.add(mtrlbtnrctnglDelete);
		
		RSMaterialButtonRectangle mtrlbtnrctnglLoginPage = new RSMaterialButtonRectangle();
		mtrlbtnrctnglLoginPage.setText("Login Page");
		mtrlbtnrctnglLoginPage.setForeground(new Color(0, 0, 255));
		mtrlbtnrctnglLoginPage.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 30));
		mtrlbtnrctnglLoginPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage home=new LoginPage();
				home.setVisible(true);
				dispose();
					JOptionPane.showMessageDialog(null, "USER Already Present");
			}
		});
		mtrlbtnrctnglLoginPage.setBackground(new Color(255, 255, 51));
		mtrlbtnrctnglLoginPage.setBounds(104, 730, 358, 70);
		panel_1.add(mtrlbtnrctnglLoginPage);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "CREATE");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(validateLogin()) {
				if(login()==true) {
					JOptionPane.showMessageDialog(null, "User Added Successfully");
				}
				else {
					JOptionPane.showMessageDialog(null, "Failed To Add User");

				}
			}
		}
	}
}



