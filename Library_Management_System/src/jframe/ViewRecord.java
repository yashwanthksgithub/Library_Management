package jframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import rojeru_san.complementos.RSTableMetro;
import rojeru_san.componentes.RSDateChooser;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import rojerusan.RSMaterialButtonCircle;
import rojerusan.RSTableMetroBeanInfo;
import javax.swing.JScrollPane;
import rojerusan.RSButtonMetro;
import rojerusan.RSMaterialButtonRectangle;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Date;

import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class ViewRecord extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRecord frame = new ViewRecord();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	rojerusan.RSTableMetro tableMetro;
	RSDateChooser date_issuedate;
	RSDateChooser duedate;
	RSDateChooser issuedate;
	DefaultTableModel model;
	private final Action action_1 = new SwingAction_1();
	private JTextField txt_bookid;
	private JTextField txt_bbokname;
	private JTextField txt_studentid;
	private JTextField txt_studentname;
	RSMaterialButtonRectangle Bidsearch_but;
	RSMaterialButtonRectangle statussearch;
	RSMaterialButtonRectangle stnamesearch;
	RSMaterialButtonRectangle st_idsearch;
	RSMaterialButtonRectangle booknamesearch;
	private final Action action = new SwingAction();
	private JTextField status;
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();
	private final Action action_4 = new SwingAction_4();
	private final Action action_5 = new SwingAction_5();
	private final Action action_6 = new SwingAction_6();
	
	
	public void clear() {
		txt_bookid.setText("");
		txt_bbokname.setText("");
		txt_studentid.setText("");
		txt_studentname.setText("");
		status.setText("");
	}
	 
public boolean validateBID() {
		String Bid=txt_bookid.getText();

		if(Bid.equals("")) {
			JOptionPane.showMessageDialog(this, "Please Enter Book ID");
			return false;
		}
		return true;
	}

public boolean validateBname() {

	String Bname=txt_bbokname.getText();
	if(Bname.equals("")) {
		JOptionPane.showMessageDialog(this, "Please Enter Book Name");
		return false;
	}
	return true;
}

public boolean validateStatus() {

	String stat=status.getText();
	if(stat.equals("")) {
		JOptionPane.showMessageDialog(this, "Please Enter Corect Status");
		return false;
	}
	return true;
}

public boolean validateSID() {
	String Sid=txt_studentid.getText();
	if(Sid.equals("")) {
		JOptionPane.showMessageDialog(this, "Please Enter Corect Student ID");
		return false;
	}
	return true;
}

public boolean validateSname() {
	String Sname=txt_studentname.getText();
	if(Sname.equals("")) {
		JOptionPane.showMessageDialog(this, "Please Enter Student Name");
		return false;
	}
	return true;
}

	 public void setbookdetalis() {
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","Yashu@123");
				Statement sr=con.createStatement();
				String query="select * from issue_book_details";
				ResultSet rs=sr.executeQuery(query);
				
				ResultSetMetaData rsmd=rs.getMetaData();
				
				 model = (DefaultTableModel)tableMetro.getModel();
				
				int cols=rsmd.getColumnCount();
				String[] colName=new String[cols];
				for(int i=0;i<cols;i++) {
					colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
				}
				String id,bookname,studentname,bookid,studentid,issuedate,duedate,stat;
				
				while(rs.next()) {
					id=rs.getString(1);
					bookid=rs.getString(2);
					bookname=rs.getString(3);
					studentid=rs.getString(4);
					studentname=rs.getString(5);
					issuedate=rs.getString(6);
					duedate=rs.getString(7);
					stat=rs.getString(8);
					String[] row= {id,bookid,bookname,studentid,studentname,issuedate,duedate,stat};
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
	
	public void search_bookid() {
		String ID=txt_bookid.getText();
		try {
			Connection con=DBConnection.getConnection();
			String sql="select * from issue_book_details where book_id = ?";
			PreparedStatement pat=con.prepareStatement(sql);
			
			pat.setString(1, ID);
			
			ResultSet rs=pat.executeQuery();
			String id,bookname,studentname,bookid,studentid,issuedate,duedate,stat;
			
			if(rs.next()==false) {
				JOptionPane.showMessageDialog(null, "No Record is Found");
			}
			else {
			do{
				id=rs.getString(1);
				bookid=rs.getString(2);
				bookname=rs.getString(3);
				studentid=rs.getString(4);
				studentname=rs.getString(5);
				issuedate=rs.getString(6);
				duedate=rs.getString(7);
				stat=rs.getString(8);
				String[] row= {id,bookid,bookname,studentid,studentname,issuedate,duedate,stat};
				model.addRow(row);
			}while(rs.next());
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void search_bookname() {
		String BID=txt_bbokname.getText();
		try {
			Connection con=DBConnection.getConnection();
			String sql="select * from issue_book_details where book_name = ?";
			PreparedStatement pat=con.prepareStatement(sql);
			
			pat.setString(1, BID);
			
			ResultSet rs=pat.executeQuery();
			String id,bookname,studentname,bookid,studentid,issuedate,duedate,stat;
			
			if(rs.next()==false) {
				JOptionPane.showMessageDialog(null, "No Record is Found");
			}
			else {
			do{
				id=rs.getString(1);
				bookid=rs.getString(2);
				bookname=rs.getString(3);
				studentid=rs.getString(4);
				studentname=rs.getString(5);
				issuedate=rs.getString(6);
				duedate=rs.getString(7);
				stat=rs.getString(8);
				String[] row= {id,bookid,bookname,studentid,studentname,issuedate,duedate,stat};
				model.addRow(row);
			}while(rs.next());
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void search_studentid() {
		String SID=txt_studentid.getText();
		try {
			Connection con=DBConnection.getConnection();
			String sql="select * from issue_book_details where student_id = ?";
			PreparedStatement pat=con.prepareStatement(sql);
			
			pat.setString(1, SID);
			
			ResultSet rs=pat.executeQuery();
			String id,bookname,studentname,bookid,studentid,issuedate,duedate,stat;
			
			if(rs.next()==false) {
				JOptionPane.showMessageDialog(null, "No Record is Found");
			}
			else {
			do{
				id=rs.getString(1);
				bookid=rs.getString(2);
				bookname=rs.getString(3);
				studentid=rs.getString(4);
				studentname=rs.getString(5);
				issuedate=rs.getString(6);
				duedate=rs.getString(7);
				stat=rs.getString(8);
				String[] row= {id,bookid,bookname,studentid,studentname,issuedate,duedate,stat};
				model.addRow(row);
			}while(rs.next());
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void search_studentname() {
		String SID=txt_studentname.getText();
		try {
			Connection con=DBConnection.getConnection();
			String sql="select * from issue_book_details where student_name = ?";
			PreparedStatement pat=con.prepareStatement(sql);
			
			pat.setString(1, SID);
			
			ResultSet rs=pat.executeQuery();
			String id,bookname,studentname,bookid,studentid,issuedate,duedate,stat;
			
			if(rs.next()==false) {
				JOptionPane.showMessageDialog(null, "No Record is Found");
			}
			else {
			do {
				id=rs.getString(1);
				bookid=rs.getString(2);
				bookname=rs.getString(3);
				studentid=rs.getString(4);
				studentname=rs.getString(5);
				issuedate=rs.getString(6);
				duedate=rs.getString(7);
				stat=rs.getString(8);
				String[] row= {id,bookid,bookname,studentid,studentname,issuedate,duedate,stat};
				model.addRow(row);
			}while(rs.next());
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void search_status() {
		String sta=status.getText();
		if(sta.equalsIgnoreCase("Pending")) {
		try {
			Connection con=DBConnection.getConnection();
			Statement sr=con.createStatement();
			String query="select * from issue_book_details where status = '"+"Pending"+"' ";
			ResultSet rs=sr.executeQuery(query);
			String id,bookname,studentname,bookid,studentid,issuedate,duedate,stat;
			
			if(rs.next()==false) {
				JOptionPane.showMessageDialog(null, "No Record is Found");
			}
			else {
			do {
				id=rs.getString(1);
				bookid=rs.getString(2);
				bookname=rs.getString(3);
				studentid=rs.getString(4);
				studentname=rs.getString(5);
				issuedate=rs.getString(6);
				duedate=rs.getString(7);
				stat=rs.getString(8);
				String[] row= {id,bookid,bookname,studentid,studentname,issuedate,duedate,stat};
				model.addRow(row);
			}while(rs.next());
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		}
		else if(sta.equalsIgnoreCase("Returned")) {
			try {
				Connection con=DBConnection.getConnection();
				Statement sr=con.createStatement();
				String query="select * from issue_book_details where status = '"+"Returned"+"' ";
				ResultSet rs=sr.executeQuery(query);
				String id,bookname,studentname,bookid,studentid,issuedate,duedate,stat;
				
				if(rs.next()==false) {
					JOptionPane.showMessageDialog(null, "No Record is Found");
				}
				else {
				do{
					id=rs.getString(1);
					bookid=rs.getString(2);
					bookname=rs.getString(3);
					studentid=rs.getString(4);
					studentname=rs.getString(5);
					issuedate=rs.getString(6);
					duedate=rs.getString(7);
					stat=rs.getString(8);
					String[] row= {id,bookid,bookname,studentid,studentname,issuedate,duedate,stat};
					model.addRow(row);
				}while(rs.next());
				}
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Something Wrong");
		}
	}
	
	public void search() {
		Date uIssueDate=issuedate.getDatoFecha();
		Date uDueDate=duedate.getDatoFecha();
		
		Long l1=uIssueDate.getTime();
		Long l2=uDueDate.getTime();
		
		java.sql.Date  sIssueDate=new java.sql.Date(l1);
		java.sql.Date  sDueDate=new java.sql.Date(l2);
		try {
			Connection con=DBConnection.getConnection();
			String sql="select * from issue_book_details where issue_date BETWEEN ? and ?";
			PreparedStatement pat=con.prepareStatement(sql);
			
			pat.setDate(1, sIssueDate);
			pat.setDate(2, sDueDate);
			
			ResultSet rs=pat.executeQuery();
			String id,bookname,studentname,bookid,studentid,issuedate,duedate,stat;
			
			if(rs.next()==false) {
				JOptionPane.showMessageDialog(null, "No Record is Found");
			}
			else {
			do {
				id=rs.getString(1);
				bookid=rs.getString(2);
				bookname=rs.getString(3);
				studentid=rs.getString(4);
				studentname=rs.getString(5);
				issuedate=rs.getString(6);
				duedate=rs.getString(7);
				stat=rs.getString(8);
				String[] row= {id,bookid,bookname,studentid,studentname,issuedate,duedate,stat};
				model.addRow(row);
			}while(rs.next());
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	
	/**
	 * Create the frame.
	 */
	public ViewRecord() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1560, 823);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		panel.setBounds(0, 0, 1560, 280);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("View All Records");
		lblNewLabel.setIcon(new ImageIcon(ViewRecord.class.getResource("/AddNewBookIcons/icons8_Literature_100px_1.png")));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setBounds(605, 10, 555, 140);
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(572, 140, 600, 7);
		panel.add(panel_2);
		
		JPanel panel_table = new JPanel();
		panel_table.setBackground(Color.WHITE);
		panel_table.setBounds(0, 278, 1560, 556);
		contentPane.add(panel_table);
		panel_table.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 945, 413);
		panel_table.add(scrollPane);
		
		tableMetro = new rojerusan.RSTableMetro();
		tableMetro.setColorFilasBackgound2(Color.WHITE);
		tableMetro.setColorBackgoundHead(Color.RED);
		scrollPane.setViewportView(tableMetro);
		
		RSMaterialButtonRectangle mtrlbtnrctnglViewtable = new RSMaterialButtonRectangle();
		mtrlbtnrctnglViewtable.setAction(action_1);
		mtrlbtnrctnglViewtable.setForeground(new Color(0, 0, 204));
		mtrlbtnrctnglViewtable.setBackground(new Color(255, 255, 0));
		mtrlbtnrctnglViewtable.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 32));
		mtrlbtnrctnglViewtable.setText("View Table");
		mtrlbtnrctnglViewtable.setBounds(293, 423, 300, 70);
		panel_table.add(mtrlbtnrctnglViewtable);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 153, 255));
		panel_3.setBounds(-10, 503, 1570, 43);
		panel_table.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Library Management System");
		lblNewLabel_4.setForeground(new Color(255, 255, 0));
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 27));
		lblNewLabel_4.setBounds(1175, 0, 375, 33);
		panel_3.add(lblNewLabel_4);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 0, 255));
		panel_4.setBounds(943, 0, 617, 504);
		panel_table.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel(" Book-Id");
		lblNewLabel_3.setIcon(new ImageIcon(ViewRecord.class.getResource("/AddNewBookIcons/icons8_Contact_26px.png")));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(10, 10, 142, 45);
		panel_4.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel(" Book Name");
		lblNewLabel_3_1.setIcon(new ImageIcon(ViewRecord.class.getResource("/AddNewBookIcons/icons8_Moleskine_26px.png")));
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3_1.setBounds(10, 99, 177, 45);
		panel_4.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Student-ID");
		lblNewLabel_3_2.setIcon(new ImageIcon(ViewRecord.class.getResource("/AddNewBookIcons/icons8_Contact_26px.png")));
		lblNewLabel_3_2.setForeground(Color.WHITE);
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3_2.setBounds(10, 187, 163, 45);
		panel_4.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Student Name");
		lblNewLabel_3_3.setIcon(new ImageIcon(ViewRecord.class.getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png")));
		lblNewLabel_3_3.setForeground(Color.WHITE);
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3_3.setBounds(10, 275, 200, 45);
		panel_4.add(lblNewLabel_3_3);
		
		txt_bookid = new JTextField();
		txt_bookid.setFont(new Font("Tahoma", Font.BOLD, 25));
		txt_bookid.setBounds(204, 10, 257, 45);
		panel_4.add(txt_bookid);
		txt_bookid.setColumns(10);
		
		txt_bbokname = new JTextField();
		txt_bbokname.setFont(new Font("Tahoma", Font.BOLD, 25));
		txt_bbokname.setColumns(10);
		txt_bbokname.setBounds(204, 99, 257, 45);
		panel_4.add(txt_bbokname);
		
		txt_studentid = new JTextField();
		txt_studentid.setFont(new Font("Tahoma", Font.BOLD, 25));
		txt_studentid.setColumns(10);
		txt_studentid.setBounds(204, 187, 257, 45);
		panel_4.add(txt_studentid);
		
		txt_studentname = new JTextField();
		txt_studentname.setFont(new Font("Tahoma", Font.BOLD, 25));
		txt_studentname.setColumns(10);
		txt_studentname.setBounds(204, 275, 257, 45);
		panel_4.add(txt_studentname);
		
		Bidsearch_but = new RSMaterialButtonRectangle();
		Bidsearch_but.setAction(action);
		Bidsearch_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateBID()) {
					clearTable();
					search_bookid();
				}
				
			}
		});
		Bidsearch_but.setFont(new Font("Dialog", Font.PLAIN, 20));
		Bidsearch_but.setText("SEARCH");
		Bidsearch_but.setBackground(Color.RED);
		Bidsearch_but.setBounds(471, 12, 136, 45);
		panel_4.add(Bidsearch_but);
		
		booknamesearch = new RSMaterialButtonRectangle();
		booknamesearch.setAction(action_2);
		booknamesearch.setText("SEARCH");
		booknamesearch.setFont(new Font("Dialog", Font.PLAIN, 20));
		booknamesearch.setBackground(Color.RED);
		booknamesearch.setBounds(471, 101, 136, 45);
		panel_4.add(booknamesearch);
		
		st_idsearch = new RSMaterialButtonRectangle();
		st_idsearch.setAction(action_3);
		st_idsearch.setText("SEARCH");
		st_idsearch.setFont(new Font("Dialog", Font.PLAIN, 20));
		st_idsearch.setBackground(Color.RED);
		st_idsearch.setBounds(471, 189, 136, 45);
		panel_4.add(st_idsearch);
		
		stnamesearch = new RSMaterialButtonRectangle();
		stnamesearch.setAction(action_4);
		stnamesearch.setText("SEARCH");
		stnamesearch.setFont(new Font("Dialog", Font.PLAIN, 20));
		stnamesearch.setBackground(Color.RED);
		stnamesearch.setBounds(471, 277, 136, 45);
		panel_4.add(stnamesearch);
		
		JLabel lblNewLabel_3_3_1 = new JLabel("  Status\r\n");
		lblNewLabel_3_3_1.setIcon(new ImageIcon(ViewRecord.class.getResource("/AddNewBookIcons/icons8_Unit_26px.png")));
		lblNewLabel_3_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_3_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3_3_1.setBounds(10, 367, 200, 45);
		panel_4.add(lblNewLabel_3_3_1);
		
		status = new JTextField();
		status.setFont(new Font("Tahoma", Font.BOLD, 25));
		status.setColumns(10);
		status.setBounds(204, 367, 257, 45);
		panel_4.add(status);
		
		statussearch = new RSMaterialButtonRectangle();
		statussearch.setAction(action_5);
		statussearch.setText("SEARCH");
		statussearch.setFont(new Font("Dialog", Font.PLAIN, 20));
		statussearch.setBackground(Color.RED);
		statussearch.setBounds(471, 369, 136, 45);
		panel_4.add(statussearch);
		
		RSMaterialButtonRectangle mtrlbtnrctnglClear = new RSMaterialButtonRectangle();
		mtrlbtnrctnglClear.setAction(action_6);
		mtrlbtnrctnglClear.setForeground(Color.RED);
		mtrlbtnrctnglClear.setText("CLEAR");
		mtrlbtnrctnglClear.setFont(new Font("Dialog", Font.PLAIN, 25));
		mtrlbtnrctnglClear.setBackground(Color.YELLOW);
		mtrlbtnrctnglClear.setBounds(228, 440, 200, 54);
		panel_4.add(mtrlbtnrctnglClear);
		
		JLabel lblNewLabel_2_4_1_1 = new JLabel("Issue Date:");
		lblNewLabel_2_4_1_1.setForeground(Color.RED);
		lblNewLabel_2_4_1_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lblNewLabel_2_4_1_1.setBounds(1035, 485, 154, 39);
		panel.add(lblNewLabel_2_4_1_1);
		
		 date_issuedate = new RSDateChooser();
		date_issuedate.setPlaceholder("Select Isssue Date");
		date_issuedate.setFuente(new Font("Rockwell Extra Bold", Font.PLAIN, 23));
		date_issuedate.setColorForeground(Color.GRAY);
		date_issuedate.setColorButtonHover(Color.BLACK);
		date_issuedate.setColorBackground(Color.RED);
		date_issuedate.setBounds(1197, 485, 308, 40);
		panel.add(date_issuedate);
		
		issuedate = new RSDateChooser();
		issuedate.setPlaceholder("Issued From");
		issuedate.setFuente(new Font("Rockwell Extra Bold", Font.PLAIN, 28));
		issuedate.setForeground(Color.BLACK);
		issuedate.setColorForeground(Color.BLACK);
		issuedate.setColorBackground(Color.RED);
		issuedate.setBounds(261, 186, 313, 53);
		panel.add(issuedate);
		
		JLabel lblNewLabel_1 = new JLabel("Issue From :");
		lblNewLabel_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(29, 175, 222, 64);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Issued To :");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(628, 175, 212, 64);
		panel.add(lblNewLabel_1_1);
		
		duedate = new RSDateChooser();
		duedate.setFuente(new Font("Rockwell Extra Bold", Font.PLAIN, 28));
		duedate.setColorForeground(new Color(0, 0, 102));
		duedate.setColorBackground(Color.YELLOW);
		duedate.setForeground(Color.BLACK);
		duedate.setPlaceholder("Issued To");
		duedate.setBounds(850, 186, 322, 53);
		panel.add(duedate);
		
		RSMaterialButtonCircle mtrlbtncrclSearch = new RSMaterialButtonCircle();
		mtrlbtncrclSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(issuedate.getDatoFecha()!=null && duedate.getDatoFecha()!=null)
				{
				clearTable();
				search();
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select Date");
				}
			}
		});
		mtrlbtncrclSearch.setBounds(1284, 172, 184, 78);
		panel.add(mtrlbtncrclSearch);
		mtrlbtncrclSearch.setBackground(new Color(255, 0, 102));
		mtrlbtncrclSearch.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
		mtrlbtncrclSearch.setText("Search");
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage home=new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
		panel_1.setBackground(Color.RED);
		panel_1.setBounds(0, 0, 158, 53);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Back");
		lblNewLabel_2.setBounds(0, 0, 148, 48);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setBackground(Color.RED);
		lblNewLabel_2.setIcon(new ImageIcon(ViewRecord.class.getResource("/AddNewBookIcons/icons8_Rewind_48px.png")));
		lblNewLabel_2.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 30));
		lblNewLabel_2.setForeground(Color.WHITE);
		
		
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			clearTable();
			setbookdetalis();
		}
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(validateBID()) {
				clearTable();
				search_bookid();
			}
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "SwingAction_2");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(validateBname()) {
				clearTable();
				search_bookname();
			}
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "SwingAction_3");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(validateSID()) {
				clearTable();
				search_studentid();
			}
		}
	}
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "SwingAction_4");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(validateSname()) {
				clearTable();
				search_studentname();
			}
		}
	}
	private class SwingAction_5 extends AbstractAction {
		public SwingAction_5() {
			putValue(NAME, "SwingAction_5");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(validateStatus()) {
				clearTable();
				search_status();
			}
		}
	}
	private class SwingAction_6 extends AbstractAction {
		public SwingAction_6() {
			putValue(NAME, "SwingAction_6");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			clear();
		}
	}
}
