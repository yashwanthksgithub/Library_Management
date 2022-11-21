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

public class IssueBook extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBook frame = new IssueBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	JLabel lbl_bookid;
	JLabel lbl_bookname ;
	JLabel lbl_author;
	JLabel lbl_quantity;
	JLabel lbl_bookerror1;
	JLabel lbl_studentid;
	JLabel lbl_studentname;
	JLabel lbl_course ;
	JLabel lbl_branch;
	JLabel lbl_studenterror1;
	JTextPane txt_bookid;
	JTextPane txt_studentid;
	RSDateChooser date_issuedate;
	RSDateChooser date_duedate;
	private final Action action = new SwingAction();
	
	
	public void getbookdetails() {
		int  bookId=Integer.parseInt(txt_bookid.getText());
		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement pt=con.prepareStatement("select * from book_details where book_id = ?");
			pt.setInt(1, bookId);
			ResultSet rs=pt.executeQuery();
			
			
			 if(rs.next()) {
				 lbl_bookid.setText(rs.getString("book_id"));
				 lbl_bookname.setText(rs.getString("book_name"));
				 lbl_author.setText(rs.getString("author"));
				 lbl_quantity.setText(rs.getString("quantity"));
				 lbl_bookerror1.setText("");
			 }else {
				 lbl_bookerror1.setText("Invalid Book_Id");
				 JOptionPane.showMessageDialog(null, "Invalid Book_Id");
				 clearbook();
			 }
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 
	}
	public void clearbook() {
		lbl_bookid.setText("");
		 lbl_bookname.setText("");
		 lbl_author.setText("");
		 lbl_quantity.setText("");
		
	}
	
	
	
	public void getstudentdetails() {
		int  StudenId=Integer.parseInt(txt_studentid.getText());
		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement pt=con.prepareStatement("select * from student_details where student_id = ?");
			pt.setInt(1, StudenId);
			ResultSet rs=pt.executeQuery();
			
			
			 if(rs.next()) {
				 lbl_studentid.setText(rs.getString("student_id"));
				 lbl_studentname.setText(rs.getString("name"));
				 lbl_course.setText(rs.getString("course"));
				 lbl_branch.setText(rs.getString("branch"));
				 lbl_studenterror1.setText("");
			 }	
			 else {
				 lbl_studenterror1.setText("Invalid Student_Id");
				 JOptionPane.showMessageDialog(null, "Invalid Student_Id");
				 
				 clearstudent() ;
			 }
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 
	}
	
	public void clearstudent() {
		lbl_studentid.setText("");
		lbl_studentname.setText("");
		lbl_course.setText("");
		lbl_branch.setText("");
		
	}
	
	public boolean issuebook() {
		boolean isIssued=false;
		int  bookId=Integer.parseInt(txt_bookid.getText());
		int  StudenId=Integer.parseInt(txt_studentid.getText());
		String bookName=lbl_bookname.getText();
		String StudentName=lbl_studentname.getText();
		
		Date uIssueDate=date_issuedate.getDatoFecha();
		Date uDueDate=date_duedate.getDatoFecha();
		
		Long l1=uIssueDate.getTime();
		Long l2=uDueDate.getTime();
		
		java.sql.Date  sIssueDate=new java.sql.Date(l1);
		java.sql.Date  sDueDate=new java.sql.Date(l2);
		
		try {
			Connection con=DBConnection.getConnection();
			String sql="insert into issue_book_details(book_id,book_name,student_id,student_name,issue_date,due_date,status) values(?,?,?,?,?,?,?)";
			PreparedStatement pat=con.prepareStatement(sql);
			pat.setInt(1, bookId);
			pat.setString(2, bookName);
			pat.setInt(3, StudenId);
			pat.setString(4, StudentName);
			pat.setDate(5, sIssueDate);
			pat.setDate(6, sDueDate);
			pat.setString(7, "Pending");
			
			int rowCount = pat.executeUpdate();
			if(rowCount>0) {
				isIssued=true;
			}else {
				isIssued=false;
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return isIssued;
	}
	
	public void updatebookcount() {
		int  bookId=Integer.parseInt(txt_bookid.getText());
		try {
			Connection con=DBConnection.getConnection();
			String sql="update book_details set quantity = quantity - 1 where book_id = ?";
			PreparedStatement pat=con.prepareStatement(sql);
			pat.setInt(1, bookId);
			
			int rowCount = pat.executeUpdate();
			if(rowCount>0) {
				JOptionPane.showMessageDialog(null, "Book Count Updated ");
				int initialcount=Integer.parseInt(lbl_quantity.getText());
				lbl_quantity.setText(Integer.toString(initialcount - 1));
				
			}else {
				JOptionPane.showMessageDialog(null, "Can't Update Book Count");

			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public boolean isAlreadyIssued() {
		
		boolean alreadyissued=false;
		int  bookId=Integer.parseInt(txt_bookid.getText());
		int  StudenId=Integer.parseInt(txt_studentid.getText());
		try {
			Connection con=DBConnection.getConnection();
			String sql="select * from issue_book_details where book_id = ? and student_id = ? and status = ?";
			PreparedStatement pat=con.prepareStatement(sql);
			pat.setInt(1, bookId);
			pat.setInt(2, StudenId);
			pat.setString(3, "Pending");
			ResultSet rs= pat.executeQuery();
			if(rs.next()) {
				alreadyissued=true;
			}else {
				alreadyissued=false;
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		
		return alreadyissued;
	}
	
	/**
	 * Create the frame.
	 */
	public IssueBook() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1560, 823);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(-13, -18, 1589, 866);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 51, 153));
		panel_1.setBounds(0, 10, 480, 791);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 0));
		panel_3.setBounds(10, 10, 133, 45);
		panel_1.add(panel_3);
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
		
		JLabel lblNewLabel_1 = new JLabel(" Book Details");
		lblNewLabel_1.setIcon(new ImageIcon(IssueBook.class.getResource("/AddNewBookIcons/icons8_Literature_100px_1.png")));
		lblNewLabel_1.setFont(new Font("Rockwell Condensed", Font.BOLD, 38));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(80, 72, 318, 149);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(69, 201, 360, 5);
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel("Book Id : ");
		lblNewLabel_2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(64, 282, 127, 39);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Book\r\nName :");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(28, 372, 150, 39);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Author :");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(77, 459, 114, 39);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Quantity :");
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_3.setBounds(51, 533, 127, 39);
		panel_1.add(lblNewLabel_2_3);
		
		lbl_bookid = new JLabel("");
		lbl_bookid.setBackground(Color.WHITE);
		lbl_bookid.setForeground(Color.BLACK);
		lbl_bookid.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl_bookid.setBounds(167, 282, 288, 39);
		panel_1.add(lbl_bookid);
		
		lbl_bookname = new JLabel("");
		lbl_bookname.setForeground(Color.BLACK);
		lbl_bookname.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl_bookname.setBackground(Color.WHITE);
		lbl_bookname.setBounds(182, 372, 288, 39);
		panel_1.add(lbl_bookname);
		
		 lbl_author = new JLabel("");
		lbl_author.setForeground(Color.BLACK);
		lbl_author.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl_author.setBackground(Color.WHITE);
		lbl_author.setBounds(182, 459, 288, 39);
		panel_1.add(lbl_author);
		
		 lbl_quantity = new JLabel("");
		lbl_quantity.setForeground(Color.BLACK);
		lbl_quantity.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl_quantity.setBackground(Color.WHITE);
		lbl_quantity.setBounds(167, 533, 288, 39);
		panel_1.add(lbl_quantity);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(IssueBook.class.getResource("/icons/bookimg (1).jpg")));
		lblNewLabel_3.setBounds(80, 582, 318, 196);
		panel_1.add(lblNewLabel_3);
		
		lbl_bookerror1 = new JLabel("");
		lbl_bookerror1.setForeground(new Color(0, 0, 153));
		lbl_bookerror1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
		lbl_bookerror1.setBackground(Color.WHITE);
		lbl_bookerror1.setBounds(69, 216, 360, 56);
		panel_1.add(lbl_bookerror1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 153, 255));
		panel_2.setBounds(490, 0, 511, 801);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel(" Student Details");
		lblNewLabel_1_1.setIcon(new ImageIcon(IssueBook.class.getResource("/AddNewBookIcons/icons8_Student_Registration_100px_2.png")));
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Rockwell Condensed", Font.BOLD, 38));
		lblNewLabel_1_1.setBounds(67, 76, 358, 149);
		panel_2.add(lblNewLabel_1_1);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(Color.WHITE);
		panel_4_1.setBounds(54, 200, 400, 5);
		panel_2.add(panel_4_1);
		
		JLabel lblNewLabel_2_5 = new JLabel("Student ID: ");
		lblNewLabel_2_5.setForeground(Color.WHITE);
		lblNewLabel_2_5.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_5.setBounds(20, 278, 150, 39);
		panel_2.add(lblNewLabel_2_5);
		
		lbl_studentid = new JLabel("");
		lbl_studentid.setForeground(Color.BLACK);
		lbl_studentid.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl_studentid.setBackground(Color.WHITE);
		lbl_studentid.setBounds(157, 278, 288, 39);
		panel_2.add(lbl_studentid);
		
		lbl_studentname = new JLabel("");
		lbl_studentname.setForeground(Color.BLACK);
		lbl_studentname.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl_studentname.setBackground(Color.WHITE);
		lbl_studentname.setBounds(157, 356, 288, 39);
		panel_2.add(lbl_studentname);
		
		 lbl_course = new JLabel("");
		lbl_course.setForeground(Color.BLACK);
		lbl_course.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl_course.setBackground(Color.WHITE);
		lbl_course.setBounds(142, 457, 288, 39);
		panel_2.add(lbl_course);
		
		lbl_branch = new JLabel("");
		lbl_branch.setForeground(Color.BLACK);
		lbl_branch.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl_branch.setBackground(Color.WHITE);
		lbl_branch.setBounds(142, 537, 288, 39);
		panel_2.add(lbl_branch);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Student :");
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(37, 356, 133, 39);
		panel_2.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Course :");
		lblNewLabel_2_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_1_2.setBounds(37, 457, 107, 39);
		panel_2.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("Branch :");
		lblNewLabel_2_1_3.setForeground(Color.WHITE);
		lblNewLabel_2_1_3.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_1_3.setBounds(34, 537, 160, 39);
		panel_2.add(lblNewLabel_2_1_3);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel(" Name ");
		lblNewLabel_2_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_1_1_1.setBounds(47, 380, 92, 39);
		panel_2.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(IssueBook.class.getResource("/icons/stdimg (1).jpg")));
		lblNewLabel_3_1.setBounds(93, 579, 318, 203);
		panel_2.add(lblNewLabel_3_1);
		
		lbl_studenterror1 = new JLabel("");
		lbl_studenterror1.setForeground(Color.RED);
		lbl_studenterror1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
		lbl_studenterror1.setBackground(Color.WHITE);
		lbl_studenterror1.setBounds(77, 215, 360, 56);
		panel_2.add(lbl_studenterror1);
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setBackground(Color.RED);
		panel_4_1_1.setBounds(1098, 200, 370, 5);
		panel.add(panel_4_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel(" Issue Books");
		lblNewLabel_1_1_1.setIcon(new ImageIcon(IssueBook.class.getResource("/AddNewBookIcons/icons8_Books_52px_1.png")));
		lblNewLabel_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1.setFont(new Font("Rockwell Condensed", Font.BOLD, 48));
		lblNewLabel_1_1_1.setBounds(1108, 91, 358, 120);
		panel.add(lblNewLabel_1_1_1);
		
		txt_bookid = new JTextPane();
		txt_bookid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!txt_bookid.getText().equals("")) {
				getbookdetails();
				}
			}
		});
		txt_bookid.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		txt_bookid.setBackground(Color.YELLOW);
		txt_bookid.setBounds(1197, 456, 308, 45);
		panel.add(txt_bookid);
		
		JLabel lblNewLabel_2_4 = new JLabel("Book Id : ");
		lblNewLabel_2_4.setForeground(Color.RED);
		lblNewLabel_2_4.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_4.setBounds(1035, 456, 127, 39);
		panel.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_4_1 = new JLabel("Student ID:");
		lblNewLabel_2_4_1.setForeground(Color.RED);
		lblNewLabel_2_4_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_4_1.setBounds(1021, 538, 154, 39);
		panel.add(lblNewLabel_2_4_1);
		
		txt_studentid = new JTextPane();
		txt_studentid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!txt_studentid.getText().equals("")) {
					getstudentdetails();
				}
			}
		});
		txt_studentid.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 25));
		txt_studentid.setBackground(Color.YELLOW);
		txt_studentid.setBounds(1197, 538, 308, 45);
		panel.add(txt_studentid);
		
		JLabel lblNewLabel_2_4_1_1 = new JLabel("Issue Date:");
		lblNewLabel_2_4_1_1.setForeground(Color.RED);
		lblNewLabel_2_4_1_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_4_1_1.setBounds(1033, 286, 154, 39);
		panel.add(lblNewLabel_2_4_1_1);
		
		 date_issuedate = new RSDateChooser();
		date_issuedate.setPlaceholder("Select Isssue Date");
		date_issuedate.setFuente(new Font("Rockwell Extra Bold", Font.PLAIN, 23));
		date_issuedate.setColorForeground(Color.GRAY);
		date_issuedate.setColorButtonHover(Color.BLACK);
		date_issuedate.setColorBackground(Color.RED);
		date_issuedate.setBounds(1197, 286, 308, 40);
		panel.add(date_issuedate);
		
		JLabel lblNewLabel_2_4_1_2 = new JLabel("Due Date :");
		lblNewLabel_2_4_1_2.setForeground(Color.RED);
		lblNewLabel_2_4_1_2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_4_1_2.setBounds(1033, 373, 154, 39);
		panel.add(lblNewLabel_2_4_1_2);
		
		date_duedate = new RSDateChooser();
		date_duedate.setFuente(new Font("Rockwell Extra Bold", Font.PLAIN, 23));
		date_duedate.setColorForeground(Color.GRAY);
		date_duedate.setColorBackground(Color.RED);
		date_duedate.setPlaceholder("Select Due Date");
		date_duedate.setBounds(1197, 373, 308, 40);
		panel.add(date_duedate);
		
		RSMaterialButtonCircle mtrlbtncrclIssueBook = new RSMaterialButtonCircle();
		mtrlbtncrclIssueBook.setAction(action);
		mtrlbtncrclIssueBook.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
		mtrlbtncrclIssueBook.setText("Issue Book");
		mtrlbtncrclIssueBook.setBackground(Color.RED);
		mtrlbtncrclIssueBook.setBounds(1136, 669, 299, 73);
		panel.add(mtrlbtncrclIssueBook);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 0, 255));
		panel_5.setBounds(10, 803, 1569, 45);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Library Management System");
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed", Font.BOLD | Font.ITALIC, 32));
		lblNewLabel_4.setForeground(new Color(255, 255, 102));
		lblNewLabel_4.setBackground(new Color(255, 255, 153));
		lblNewLabel_4.setBounds(1191, 0, 342, 35);
		panel_5.add(lblNewLabel_4);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(lbl_quantity.getText().equals("0")) {
				JOptionPane.showMessageDialog(null, "Book Is Not Available");

			}else {
			
			if(isAlreadyIssued()==false) {
				if(issuebook()==true) {
					JOptionPane.showMessageDialog(null, "Book Issued Sucessfully");
					updatebookcount();
				}else {
					JOptionPane.showMessageDialog(null, "Can't Issue The Book");

				}
			}
			else {
				JOptionPane.showMessageDialog(null, "This Student Already Have This Book");

			}
			
			}
			
		}
	}
}
