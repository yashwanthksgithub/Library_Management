package jframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import rojeru_san.componentes.RSDateChooser;
import rojeru_san.componentes.RSDateChooserBeanInfo;
import rojerusan.RSButtonMetro;
import rojerusan.RSMaterialButtonCircle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class ReturnBook extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook frame = new ReturnBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	JLabel lbl_issuedid;
	JLabel lbl_studentname ;
	JLabel lbl_bookname;
	JLabel lbl_issuedate;
	JLabel lbl_bookerror1;
	JTextPane txt_bookid;
	JTextPane txt_studentid;
	JLabel lbl_duedate;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	
	
	
	public void getIssueBookdetails() {
		int  bookId=Integer.parseInt(txt_bookid.getText());
		int  StudentId=Integer.parseInt(txt_studentid.getText());
		try {
			Connection con=DBConnection.getConnection();
			String sql="select * from issue_book_details where book_id = ? and student_id = ? and status = ?";
			PreparedStatement pat=con.prepareStatement(sql);
			pat.setInt(1, bookId);
			pat.setInt(2, StudentId);
			pat.setString(3, "Pending");
			
			ResultSet rs=pat.executeQuery();
			if(rs.next()) {
				lbl_issuedid.setText(rs.getString("id"));
				lbl_bookname.setText(rs.getString("book_name"));
				lbl_studentname.setText(rs.getString("student_name"));
				lbl_issuedate.setText(rs.getString("issue_date"));
				lbl_duedate.setText(rs.getString("due_date"));
				lbl_bookerror1.setText("");
			}
			else{
				clear();
				JOptionPane.showMessageDialog(null, "No Record Found");
//				lbl_bookerror1.setText("No Record Found");
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void clear() {
		lbl_issuedid.setText("");
		lbl_bookname.setText("");
		lbl_studentname.setText("");
		lbl_issuedate.setText("");
		lbl_duedate.setText("");
		lbl_bookerror1.setText("");
	}
	
	
	
	public void updatebookcount() {
		int  bookId=Integer.parseInt(txt_bookid.getText());
		try {
			Connection con=DBConnection.getConnection();
			String sql="update book_details set quantity = quantity + 1 where book_id = ?";
			PreparedStatement pat=con.prepareStatement(sql);
			pat.setInt(1, bookId);
			
			int rowCount = pat.executeUpdate();
			if(rowCount>0) {
				JOptionPane.showMessageDialog(null, "Book Count Updated ");
				
			}else {
				JOptionPane.showMessageDialog(null, "Can't Update Book Count");

			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public boolean returnbook() {
		boolean returned=false;
		int  bookId=Integer.parseInt(txt_bookid.getText());
		int  StudentId=Integer.parseInt(txt_studentid.getText());
		
		
		try {
			Connection con=DBConnection.getConnection();
			String sql="update issue_book_details set status = ? where student_id = ? and book_id = ? and status = ?";
			PreparedStatement pat=con.prepareStatement(sql);
			pat.setString(1, "Returned");
			pat.setInt(2, StudentId);
			pat.setInt(3, bookId);
			pat.setString(4,"Pending");
			int rowCount = pat.executeUpdate();
			if(rowCount>0) {
				returned=true;
				
			}else {
				returned=false;
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return returned;
	}
	
	
	/**
	 * Create the frame.
	 */
	public ReturnBook() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1560, 823);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1606, 790);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 51, 153));
		panel_1.setBounds(622, 0, 436, 790);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(" Book Details");
		lblNewLabel_1.setIcon(new ImageIcon(IssueBook.class.getResource("/AddNewBookIcons/icons8_Literature_100px_1.png")));
		lblNewLabel_1.setFont(new Font("Rockwell Condensed", Font.BOLD, 38));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(68, 53, 318, 149);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(46, 183, 350, 5);
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel(" Book Id : ");
		lblNewLabel_2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(18, 271, 127, 39);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel(" Student :");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(18, 331, 114, 39);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Book Name :");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(0, 410, 157, 39);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel(" IssueDate:");
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_3.setBounds(0, 472, 138, 39);
		panel_1.add(lblNewLabel_2_3);
		
		lbl_issuedid = new JLabel("");
		lbl_issuedid.setBackground(Color.WHITE);
		lbl_issuedid.setForeground(Color.BLACK);
		lbl_issuedid.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl_issuedid.setBounds(142, 271, 284, 39);
		panel_1.add(lbl_issuedid);
		
		lbl_studentname = new JLabel("");
		lbl_studentname.setForeground(Color.BLACK);
		lbl_studentname.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl_studentname.setBackground(Color.WHITE);
		lbl_studentname.setBounds(142, 331, 284, 39);
		panel_1.add(lbl_studentname);
		
		 lbl_bookname = new JLabel("");
		lbl_bookname.setForeground(Color.BLACK);
		lbl_bookname.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl_bookname.setBackground(Color.WHITE);
		lbl_bookname.setBounds(156, 410, 270, 39);
		panel_1.add(lbl_bookname);
		
		 lbl_issuedate = new JLabel("");
		lbl_issuedate.setForeground(Color.BLACK);
		lbl_issuedate.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl_issuedate.setBackground(Color.WHITE);
		lbl_issuedate.setBounds(148, 472, 276, 39);
		panel_1.add(lbl_issuedate);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(IssueBook.class.getResource("/icons/bookimg (1).jpg")));
		lblNewLabel_3.setBounds(68, 584, 302, 196);
		panel_1.add(lblNewLabel_3);
		
		lbl_bookerror1 = new JLabel("");
		lbl_bookerror1.setForeground(new Color(0, 0, 153));
		lbl_bookerror1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
		lbl_bookerror1.setBackground(Color.WHITE);
		lbl_bookerror1.setBounds(36, 212, 360, 56);
		panel_1.add(lbl_bookerror1);
		
		JLabel lblNewLabel_2_2_1 = new JLabel(" Name");
		lblNewLabel_2_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_2_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_2_1.setBounds(24, 351, 114, 39);
		panel_1.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_2_3 = new JLabel("  DueDate :");
		lblNewLabel_2_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_2_3.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_2_3.setBounds(5, 535, 127, 39);
		panel_1.add(lblNewLabel_2_2_3);
		
		lbl_duedate = new JLabel("");
		lbl_duedate.setForeground(Color.BLACK);
		lbl_duedate.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl_duedate.setBackground(Color.WHITE);
		lbl_duedate.setBounds(142, 535, 270, 39);
		panel_1.add(lbl_duedate);
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setBackground(Color.RED);
		panel_4_1_1.setBounds(1132, 188, 370, 5);
		panel.add(panel_4_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel(" Return Books");
		lblNewLabel_1_1_1.setIcon(new ImageIcon(IssueBook.class.getResource("/AddNewBookIcons/icons8_Books_52px_1.png")));
		lblNewLabel_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1.setFont(new Font("Rockwell Condensed", Font.BOLD, 48));
		lblNewLabel_1_1_1.setBounds(1144, 73, 358, 120);
		panel.add(lblNewLabel_1_1_1);
		
		txt_bookid = new JTextPane();
		txt_bookid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		txt_bookid.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		txt_bookid.setBackground(Color.YELLOW);
		txt_bookid.setBounds(1220, 280, 294, 45);
		panel.add(txt_bookid);
		
		JLabel lblNewLabel_2_4 = new JLabel("Book Id : ");
		lblNewLabel_2_4.setForeground(Color.RED);
		lblNewLabel_2_4.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_4.setBounds(1095, 286, 127, 39);
		panel.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_4_1 = new JLabel("Student ID:");
		lblNewLabel_2_4_1.setForeground(Color.RED);
		lblNewLabel_2_4_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_4_1.setBounds(1068, 375, 154, 39);
		panel.add(lblNewLabel_2_4_1);
		
		txt_studentid = new JTextPane();
		txt_studentid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		txt_studentid.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		txt_studentid.setBackground(Color.YELLOW);
		txt_studentid.setBounds(1223, 375, 291, 45);
		panel.add(txt_studentid);
		
		RSMaterialButtonCircle mtrlbtncrclIssueBook = new RSMaterialButtonCircle();
		mtrlbtncrclIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getIssueBookdetails();
			}
		});
		mtrlbtncrclIssueBook.setAction(action);
		mtrlbtncrclIssueBook.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
		mtrlbtncrclIssueBook.setText("Find");
		mtrlbtncrclIssueBook.setBackground(Color.BLUE);
		mtrlbtncrclIssueBook.setBounds(1164, 507, 299, 73);
		panel.add(mtrlbtncrclIssueBook);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 133, 45);
		panel.add(panel_3);
		panel_3.setBackground(new Color(255, 255, 0));
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Back");
		lblNewLabel.setBounds(0, 0, 129, 44);
		panel_3.add(lblNewLabel);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage home=new HomePage();
				home.setVisible(true);
				dispose();
				
			}
		});
		lblNewLabel.setIcon(new ImageIcon(IssueBook.class.getResource("/AddNewBookIcons/icons8_Rewind_48px.png")));
		lblNewLabel.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 24));
		
		RSMaterialButtonCircle mtrlbtncrclReturn = new RSMaterialButtonCircle();
		mtrlbtncrclReturn.setAction(action_1);
		mtrlbtncrclReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(returnbook()==true) {
					JOptionPane.showMessageDialog(null, "Book Returned Successfully");
					updatebookcount();
				}else {
					JOptionPane.showMessageDialog(null, "Book Fail To Return ");

				}
			}
		});
		mtrlbtncrclReturn.setText("Return");
		mtrlbtncrclReturn.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
		mtrlbtncrclReturn.setBackground(Color.RED);
		mtrlbtncrclReturn.setBounds(1164, 644, 299, 73);
		panel.add(mtrlbtncrclReturn);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(ReturnBook.class.getResource("/icons/library-2 (2).jpg")));
		lblNewLabel_4.setBounds(0, 127, 630, 590);
		panel.add(lblNewLabel_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 255));
		panel_2.setBounds(0, 787, 1560, 36);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Library Management System");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel_5.setForeground(new Color(255, 255, 51));
		lblNewLabel_5.setBounds(27, 0, 362, 36);
		panel_2.add(lblNewLabel_5);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			
		}
	}

	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
