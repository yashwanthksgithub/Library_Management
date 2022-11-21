package jframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import rojerusan.RSButtonHover;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import rojeru_san.complementos.RSTableMetro;	
import rojerusan.RSTableMetroBeanInfo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Container;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import rojerusan.RSMaterialButtonRectangle;
import rojerusan.RSMaterialButtonCircle;

public class HomePage extends JFrame {
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
		});
	}	
	RSTableMetro tableMetro_1;
	String book_name,author;
	int  book_id,quantity;
	DefaultTableModel model;
	RSMaterialButtonRectangle bookview;
	RSMaterialButtonRectangle studentview;
	RSTableMetro booktable;
	RSTableMetro studenttable;
	JLabel lbl_books;
	JLabel lbl_students;
	JLabel lbl_issuebooks;
	JLabel lbl_defaulterbooks;
	
	Color Mouseenter =new Color(0,0,0);
	Color MouseExist =new Color(0,0,102);
	
public void setBookDetailsTotable() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","Yashu@123");
			Statement sr=con.createStatement();
			ResultSet rs=sr.executeQuery("select * from book_details");
			
			
			while(rs.next()) {
				int BookId=rs.getInt("book_id");
				String BookName=rs.getString("book_name");
				String Author=rs.getString("author");
				int qunt=rs.getInt("quantity");
				
				Object[] obj= {BookId,BookName,Author,qunt};
//				AbstractButton tableMetro_1 = null;
				model = (DefaultTableModel)tableMetro_1.getModel();
				model.addRow(obj);
				
			}
				
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}




	
	public void setStudentdetalis() {
	 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","Yashu@123");
			Statement sr=con.createStatement();
			String query="select * from student_details";
			ResultSet rs=sr.executeQuery(query);
			
			ResultSetMetaData rsmd=rs.getMetaData();
			
			DefaultTableModel model = (DefaultTableModel)studenttable.getModel();
			int cols=rsmd.getColumnCount();
			String[] colName=new String[cols];
			for(int i=0;i<cols;i++) {
				colName[i]=rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colName);
			}
			String StudentId,StudentName,Course,Branch;
			
			while(rs.next()) {
				StudentId=rs.getString(1);
				StudentName=rs.getString(2);
				Course=rs.getString(3);
				Branch=rs.getString(4);
				String[] row= {StudentId,StudentName,Course,Branch};
				model.addRow(row);
				
			}
			sr.close();
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	
	
	public void setbookdetalis() {
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","Yashu@123");
				Statement sr=con.createStatement();
				String query="select * from book_details";
				ResultSet rs=sr.executeQuery(query);
				
				ResultSetMetaData rsmd=rs.getMetaData();
				
				DefaultTableModel model = (DefaultTableModel)booktable.getModel();
				
				int cols=rsmd.getColumnCount();
				String[] colName=new String[cols];
				for(int i=0;i<cols;i++) {
					colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
				}
				String id,name,author,qunt;
				
				while(rs.next()) {
					id=rs.getString(1);
					name=rs.getString(2);
					author=rs.getString(3);
					qunt=rs.getString(4);
					String[] row= {id,name,author,qunt};
					model.addRow(row);
					
				}
				sr.close();
				con.close();
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
	 }
	
	 public void clearstudent() {
			DefaultTableModel model = (DefaultTableModel)studenttable.getModel();
			model.setRowCount(0);
	 }
	 
	 public void clearbook() {
			DefaultTableModel model = (DefaultTableModel)booktable.getModel();
			model.setRowCount(0);
	 }
	 
	 public void setdatatocards() {
		 String query;
		 Statement st=null;
		 ResultSet rs=null;
		 Date date=new Date();
		 java.sql.Date todaysDate=new java.sql.Date(date.getTime());
		 PreparedStatement prepStmt;
		 
		 try {
			 
			 	Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","Yashu@123");
				prepStmt = con.prepareStatement("select * from book_details",
		                  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
				 rs = prepStmt.executeQuery();
				 rs.last();
				 lbl_books.setText(Integer.toString(rs.getRow()));

				 
				 prepStmt = con.prepareStatement("select * from student_details",
		                  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
				 rs = prepStmt.executeQuery();
				 rs.last();
				 lbl_students.setText(Integer.toString(rs.getRow()));
				 
				 prepStmt = con.prepareStatement("select * from student_details",
		                  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
				 rs = prepStmt.executeQuery();
				 rs.last();
				 
				 
				 prepStmt = con.prepareStatement("select * from issue_book_details",
		                  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
				 rs = prepStmt.executeQuery();
				 rs.last();
				 lbl_issuebooks.setText(Integer.toString(rs.getRow()));

				 prepStmt = con.prepareStatement("select * from issue_book_details where due_date < '"+ todaysDate +"' and status = '"+"Pending"+"' ",
		                  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
				 rs = prepStmt.executeQuery();
				 rs.last();
				lbl_defaulterbooks.setText(Integer.toString(rs.getRow()));
				
		 }
		 catch(Exception e) {
			 System.out.println(e);
		 }
		 
		 
	 }
	 
	 
	 
	/**
	 * Create the frame.
	 */
	public HomePage() {
		setUndecorated(true);
		setLocation(new Point(1532, 832));
		setSize(new Dimension(1532, 832));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		setBounds(100, 100, 1523, 844);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(1532, 832));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(0, 0, 1900, 70));
		panel.setBackground(new Color(0, 0, 255));
		panel.setBounds(0, 0, 1578, 70);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_menu_48px_1.png")));
		lblNewLabel.setBounds(24, 11, 45, 32);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(90, 0, 10, 70);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Library Management System ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(110, 12, 317, 31);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Welcome Admin");
		lblNewLabel_1_1.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/male_user_50px.png")));
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 27));
		lblNewLabel_1_1.setBounds(1192, 11, 317, 49);
		panel.add(lblNewLabel_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 102));
		panel_2.setBounds(0, 69, 237, 775);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.RED);
		panel_3.setBounds(0, 0, 340, 60);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("   Home Page");
		lblNewLabel_2.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_Home_26px_2.png")));
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 23));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 10, 160, 40);
		panel_3.add(lblNewLabel_2);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBackground(new Color(0, 0, 102));
		panel_3_1.setBounds(0, 58, 340, 60);
		panel_2.add(panel_3_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("    LMS DashBoard");

		lblNewLabel_2_1.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_Library_26px_1.png")));
		lblNewLabel_2_1.setForeground(Color.CYAN);
		lblNewLabel_2_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 23));
		lblNewLabel_2_1.setBackground(Color.WHITE);
		lblNewLabel_2_1.setBounds(10, 10, 241, 40);
		panel_3_1.add(lblNewLabel_2_1);
		
		JPanel panel_3_3 = new JPanel();
		panel_3_3.setLayout(null);
		panel_3_3.setBackground(new Color(0, 0, 102));
		panel_3_3.setBounds(0, 165, 340, 60);
		panel_2.add(panel_3_3);
		
		JLabel lblNewLabel_2_3 = new JLabel("    Manages Books");
		lblNewLabel_2_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ManageBooks books=new ManageBooks();
				books.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_3.setBackground(Mouseenter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_3.setBackground(MouseExist);
			}
		});
		lblNewLabel_2_3.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_Book_26px.png")));
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 23));
		lblNewLabel_2_3.setBackground(Color.WHITE);
		lblNewLabel_2_3.setBounds(10, 10, 202, 40);
		panel_3_3.add(lblNewLabel_2_3);
		
		JPanel panel_3_4 = new JPanel();
		panel_3_4.setLayout(null);
		panel_3_4.setBackground(new Color(0, 0, 102));
		panel_3_4.setBounds(0, 220, 340, 60);
		panel_2.add(panel_3_4);
		
		JLabel lblNewLabel_2_4 = new JLabel("    Manage Students");
		lblNewLabel_2_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ManageStudents student=new ManageStudents();
				student.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_4.setBackground(Mouseenter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_4.setBackground(MouseExist);
			}
		});
		lblNewLabel_2_4.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_Read_Online_26px.png")));
		lblNewLabel_2_4.setForeground(Color.WHITE);
		lblNewLabel_2_4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 23));
		lblNewLabel_2_4.setBackground(Color.WHITE);
		lblNewLabel_2_4.setBounds(10, 10, 240, 40);
		panel_3_4.add(lblNewLabel_2_4);
		
		JPanel panel_3_5 = new JPanel();
		panel_3_5.setLayout(null);
		panel_3_5.setBackground(new Color(0, 0, 102));
		panel_3_5.setBounds(0, 279, 340, 60);
		panel_2.add(panel_3_5);
		
		JLabel lblNewLabel_2_5 = new JLabel("    Issue Book");
		lblNewLabel_2_5.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				IssueBook books=new IssueBook();
				books.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_5.setBackground(Mouseenter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_5.setBackground(MouseExist);
			}
		});
		lblNewLabel_2_5.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_Sell_26px.png")));
		lblNewLabel_2_5.setForeground(Color.WHITE);
		lblNewLabel_2_5.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 23));
		lblNewLabel_2_5.setBackground(Color.WHITE);
		lblNewLabel_2_5.setBounds(10, 10, 201, 40);
		panel_3_5.add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Features");
		lblNewLabel_2_3_1.setForeground(Color.WHITE);
		lblNewLabel_2_3_1.setFont(new Font("Wide Latin", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_2_3_1.setBackground(Color.WHITE);
		lblNewLabel_2_3_1.setBounds(0, 115, 219, 40);
		panel_2.add(lblNewLabel_2_3_1);
		
		JPanel panel_3_5_1 = new JPanel();
		panel_3_5_1.setLayout(null);
		panel_3_5_1.setBackground(new Color(0, 0, 102));
		panel_3_5_1.setBounds(0, 334, 340, 60);
		panel_2.add(panel_3_5_1);
		
		JLabel lblNewLabel_2_5_1 = new JLabel("    Return Book");
		lblNewLabel_2_5_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ReturnBook books=new ReturnBook();
				books.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_5_1.setBackground(Mouseenter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_5_1.setBackground(MouseExist);
			}
		});
		lblNewLabel_2_5_1.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_Return_Purchase_26px.png")));
		lblNewLabel_2_5_1.setForeground(Color.WHITE);
		lblNewLabel_2_5_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 23));
		lblNewLabel_2_5_1.setBackground(Color.WHITE);
		lblNewLabel_2_5_1.setBounds(10, 10, 160, 40);
		panel_3_5_1.add(lblNewLabel_2_5_1);
		
		JPanel panel_3_5_2 = new JPanel();
		panel_3_5_2.setLayout(null);
		panel_3_5_2.setBackground(new Color(0, 0, 102));
		panel_3_5_2.setBounds(0, 390, 340, 60);
		panel_2.add(panel_3_5_2);
		
		JLabel lblNewLabel_2_5_2 = new JLabel("    View Records");
		lblNewLabel_2_5_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ViewRecord books=new ViewRecord();
				books.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_5_2.setBackground(Mouseenter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_5_2.setBackground(MouseExist);
			}
		});
		lblNewLabel_2_5_2.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_View_Details_26px.png")));
		lblNewLabel_2_5_2.setForeground(Color.WHITE);
		lblNewLabel_2_5_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 23));
		lblNewLabel_2_5_2.setBackground(Color.WHITE);
		lblNewLabel_2_5_2.setBounds(10, 10, 199, 40);
		panel_3_5_2.add(lblNewLabel_2_5_2);
		
		JPanel panel_3_5_3 = new JPanel();
		panel_3_5_3.setLayout(null);
		panel_3_5_3.setBackground(new Color(0, 0, 102));
		panel_3_5_3.setBounds(0, 445, 340, 60);
		panel_2.add(panel_3_5_3);
		
		JLabel lblNewLabel_2_5_3 = new JLabel("    View Issued Book");
		lblNewLabel_2_5_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IssuedBookDetails books=new IssuedBookDetails();
				books.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_5_3.setBackground(Mouseenter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_5_3.setBackground(MouseExist);
			}
		});
		lblNewLabel_2_5_3.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_Books_26px.png")));
		lblNewLabel_2_5_3.setForeground(Color.WHITE);
		lblNewLabel_2_5_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 23));
		lblNewLabel_2_5_3.setBackground(Color.WHITE);
		lblNewLabel_2_5_3.setBounds(10, 10, 219, 40);
		panel_3_5_3.add(lblNewLabel_2_5_3);
		
		JPanel panel_3_5_3_1 = new JPanel();
		panel_3_5_3_1.setLayout(null);
		panel_3_5_3_1.setBackground(new Color(0, 0, 102));
		panel_3_5_3_1.setBounds(0, 495, 340, 60);
		panel_2.add(panel_3_5_3_1);
		
		JLabel lblNewLabel_2_5_3_1 = new JLabel("    Defaulter List");
		lblNewLabel_2_5_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaulterList books=new DefaulterList();
				books.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_5_3_1.setBackground(Mouseenter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_5_3_1.setBackground(MouseExist);
			}
		});
		lblNewLabel_2_5_3_1.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_Conference_26px.png")));
		lblNewLabel_2_5_3_1.setForeground(Color.WHITE);
		lblNewLabel_2_5_3_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 23));
		lblNewLabel_2_5_3_1.setBackground(Color.WHITE);
		lblNewLabel_2_5_3_1.setBounds(10, 10, 219, 40);
		panel_3_5_3_1.add(lblNewLabel_2_5_3_1);
		
		JPanel panel_3_5_3_2 = new JPanel();
		panel_3_5_3_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				JOptionPane.showMessageDialog(null, "You are going To LogOut");
				if(JOptionPane.showConfirmDialog(null, "You are going To Logout", "LOGOUT", JOptionPane.YES_NO_OPTION)==0) {
				System.exit(ABORT);
				}
				else {
					JOptionPane.showMessageDialog(null, "Your Acount Is Still Login");
				}
			}
		});
		panel_3_5_3_2.setLayout(null);
		panel_3_5_3_2.setBackground(Color.CYAN);
		panel_3_5_3_2.setBounds(0, 642, 340, 60);
		panel_2.add(panel_3_5_3_2);
		
		JLabel lblNewLabel_2_5_3_2 = new JLabel("    Logout");
		lblNewLabel_2_5_3_2.setBounds(10, 10, 219, 40);
		panel_3_5_3_2.add(lblNewLabel_2_5_3_2);
		lblNewLabel_2_5_3_2.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_Exit_26px_2.png")));
		lblNewLabel_2_5_3_2.setForeground(Color.RED);
		lblNewLabel_2_5_3_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		lblNewLabel_2_5_3_2.setBackground(Color.WHITE);
		
		JPanel panel_3_5_1_1 = new JPanel();
		panel_3_5_1_1.setLayout(null);
		panel_3_5_1_1.setBackground(new Color(0, 0, 102));
		panel_3_5_1_1.setBounds(0, 558, 340, 60);
		panel_2.add(panel_3_5_1_1);
		
		JLabel lblNewLabel_2_5_1_1 = new JLabel("   Additionals");
		lblNewLabel_2_5_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
						Additions game=new Additions();
						game.setVisible(true);
						dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_5_1_1.setBackground(Mouseenter);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_5_1_1.setBackground(MouseExist);
			}
			
		});
		lblNewLabel_2_5_1_1.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_View_Details_26px.png")));
		lblNewLabel_2_5_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_5_1_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 23));
		lblNewLabel_2_5_1_1.setBackground(Color.WHITE);
		lblNewLabel_2_5_1_1.setBounds(10, 10, 160, 40);
		panel_3_5_1_1.add(lblNewLabel_2_5_1_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(232, 69, 1298, 794);
		contentPane.add(panel_4);
		panel_4.setBackground(Color.WHITE);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new MatteBorder(15, 0, 0, 0, (Color) Color.RED));
		panel_5.setBounds(40, 97, 234, 123);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		
		lbl_books = new JLabel("");
		lbl_books.setForeground(Color.BLACK);
		lbl_books.setBackground(Color.WHITE);
		lbl_books.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_Book_Shelf_50px.png")));
		lbl_books.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lbl_books.setBounds(54, 32, 122, 83);
		panel_5.add(lbl_books);
		
		JLabel lblNewLabel_3 = new JLabel("NO. Of  Books");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_3.setBounds(65, 62, 199, 40);
		panel_4.add(lblNewLabel_3);
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setLayout(null);
		panel_5_1.setBorder(new MatteBorder(15, 0, 0, 0, (Color) Color.RED));
		panel_5_1.setBounds(346, 97, 224, 123);
		panel_4.add(panel_5_1);
		
		lbl_students = new JLabel("");
		lbl_students.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_People_50px.png")));
		lbl_students.setForeground(Color.BLACK);
		lbl_students.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lbl_students.setBackground(Color.WHITE);
		lbl_students.setBounds(59, 34, 122, 83);
		panel_5_1.add(lbl_students);
		
		JLabel lblNewLabel_3_2 = new JLabel("NO. Of  Students");
		lblNewLabel_3_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_3_2.setBounds(354, 62, 199, 40);
		panel_4.add(lblNewLabel_3_2);
		
		JPanel panel_5_2 = new JPanel();
		panel_5_2.setLayout(null);
		panel_5_2.setBorder(new MatteBorder(15, 0, 0, 0, (Color) Color.RED));
		panel_5_2.setBounds(631, 97, 234, 123);
		panel_4.add(panel_5_2);
		
		lbl_issuebooks = new JLabel("");
		lbl_issuebooks.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_Sell_50px.png")));
		lbl_issuebooks.setForeground(Color.BLACK);
		lbl_issuebooks.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lbl_issuebooks.setBackground(Color.WHITE);
		lbl_issuebooks.setBounds(59, 34, 122, 83);
		panel_5_2.add(lbl_issuebooks);
		
		JLabel lblNewLabel_3_3 = new JLabel("NO. Of Issued Books");
		lblNewLabel_3_3.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_3_3.setBounds(631, 62, 216, 40);
		panel_4.add(lblNewLabel_3_3);
		
		JPanel panel_5_4 = new JPanel();
		panel_5_4.setLayout(null);
		panel_5_4.setBorder(new MatteBorder(15, 0, 0, 0, (Color) Color.RED));
		panel_5_4.setBounds(933, 97, 250, 123);
		panel_4.add(panel_5_4);
		
		lbl_defaulterbooks = new JLabel("");
		lbl_defaulterbooks.setIcon(new ImageIcon(HomePage.class.getResource("/adminIcons/icons8_List_of_Thumbnails_50px.png")));
		lbl_defaulterbooks.setForeground(Color.BLACK);
		lbl_defaulterbooks.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lbl_defaulterbooks.setBackground(Color.WHITE);
		lbl_defaulterbooks.setBounds(55, 23, 122, 83);
		panel_5_4.add(lbl_defaulterbooks);
		
		JLabel lblNewLabel_3_5 = new JLabel("Defaulter List");
		lblNewLabel_3_5.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_3_5.setBounds(960, 62, 219, 40);
		panel_4.add(lblNewLabel_3_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 270, 678, 172);
		panel_4.add(scrollPane);
		
		studenttable = new RSTableMetro();
		studenttable.setFuenteFilasSelect(new Font("Tahoma", Font.BOLD, 14));
		studenttable.setFuenteHead(new Font("Tahoma", Font.BOLD, 20));
		studenttable.setFuenteFilas(new Font("Tahoma", Font.BOLD, 14));
		studenttable.setColorFilasBackgound2(Color.WHITE);
		studenttable.setFont(new Font("Tahoma", Font.BOLD, 15));
		studenttable.setColorFilasForeground1(new Color(0, 0, 51));
		studenttable.setColorFilasForeground2(new Color(0, 0, 51));
		studenttable.setColorBackgoundHead(new Color(255, 0, 204));
		studenttable.setModel(new DefaultTableModel(
			new Object[][] {
				{},
				{},
				{},
				{},
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(studenttable);
		
		JLabel lblNewLabel_3_4 = new JLabel("Student Details");
		lblNewLabel_3_4.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_3_4.setBounds(50, 228, 199, 40);
		panel_4.add(lblNewLabel_3_4);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(44, 506, 678, 238);
		panel_4.add(scrollPane_3);
		
		booktable = new RSTableMetro();
		booktable.setForeground(Color.BLACK);
		booktable.setColorFilasBackgound2(Color.WHITE);
		booktable.setColorBackgoundHead(Color.MAGENTA);
		booktable.setModel(new DefaultTableModel(
			new Object[][] {
				{},
				{},
				{},
				{},
			},
			new String[] {
			}
		));
		scrollPane_3.setViewportView(booktable);
		
		JLabel lblNewLabel_3_4_1 = new JLabel("Books Details");
		lblNewLabel_3_4_1.setBounds(65, 467, 155, 29);
		panel_4.add(lblNewLabel_3_4_1);
		lblNewLabel_3_4_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(HomePage.class.getResource("/icons/th.jpeg")));
		lblNewLabel_4.setBackground(Color.RED);
		lblNewLabel_4.setBounds(786, 354, 468, 315);
		panel_4.add(lblNewLabel_4);
		
		studentview = new RSMaterialButtonRectangle();
		studentview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearstudent();
				setStudentdetalis();
			}
		});
		studentview.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 24));
		studentview.setForeground(new Color(0, 0, 153));
		studentview.setText("View");
		studentview.setBackground(Color.YELLOW);
		studentview.setBounds(230, 219, 155, 57);
		panel_4.add(studentview);
		
		 bookview = new RSMaterialButtonRectangle();
		
		bookview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearbook();
				setbookdetalis();
			}
		});
		bookview.setText("View");
		bookview.setForeground(new Color(0, 0, 153));
		bookview.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 24));
		bookview.setBackground(Color.YELLOW);
		bookview.setBounds(230, 452, 155, 57);
		panel_4.add(bookview);
		
		RSMaterialButtonRectangle studentview_1 = new RSMaterialButtonRectangle();
		studentview_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setdatatocards() ;
			}
		});
		studentview_1.setText("View");
		studentview_1.setForeground(new Color(0, 0, 153));
		studentview_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 24));
		studentview_1.setBackground(Color.YELLOW);
		studentview_1.setBounds(538, 10, 155, 57);
		panel_4.add(studentview_1);
		
	}
}
