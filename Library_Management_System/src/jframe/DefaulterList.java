package jframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import rojerusan.RSMaterialButtonRectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class DefaulterList extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefaulterList frame = new DefaulterList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	rojerusan.RSTableMetro tableMetro;
	RSMaterialButtonRectangle mtrlbtnrctnglViewtable;
	DefaultTableModel model;
	
	 public void setbookdetalis() {
		 Date date=new Date();
		 java.sql.Date todaysDate=new java.sql.Date(date.getTime());
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","Yashu@123");
				PreparedStatement sr=con.prepareStatement("select * from issue_book_details where due_date < ? and status = ? ");
				sr.setDate(1, todaysDate);
				sr.setString(2, "Pending");
				ResultSet rs=sr.executeQuery();
				
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
	
	 
	 
	/**
	 * Create the frame.
	 */
	public DefaulterList() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1560, 823);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1560, 823);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 313, 994, 357);
		panel.add(scrollPane);
		
		tableMetro = new rojerusan.RSTableMetro();
		tableMetro.setColorForegroundHead(Color.BLACK);
		tableMetro.setColorFilasBackgound1(Color.BLACK);
		tableMetro.setColorSelBackgound(Color.BLUE);
		tableMetro.setColorFilasForeground2(Color.WHITE);
		tableMetro.setColorFilasForeground1(Color.WHITE);
		tableMetro.setColorFilasBackgound2(Color.BLACK);
		tableMetro.setColorBackgoundHead(Color.RED);
		scrollPane.setViewportView(tableMetro);
		
		
		 mtrlbtnrctnglViewtable = new RSMaterialButtonRectangle();
		 mtrlbtnrctnglViewtable.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
//		 		if()
		 		
		 		clearTable();
				setbookdetalis();
		 	}
		 });
		mtrlbtnrctnglViewtable.setText("View Table");
		mtrlbtnrctnglViewtable.setForeground(new Color(0, 0, 204));
		mtrlbtnrctnglViewtable.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 32));
		mtrlbtnrctnglViewtable.setBackground(Color.YELLOW);
		mtrlbtnrctnglViewtable.setBounds(27, 680, 300, 70);
		panel.add(mtrlbtnrctnglViewtable);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(DefaulterList.class.getResource("/icons/booksdef.jpeg")));
		lblNewLabel_2.setBounds(1048, 336, 474, 334);
		panel.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(153, 204, 255));
		panel_3.setBounds(0, 0, 1550, 238);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("  Defaulter List");
		lblNewLabel.setBounds(467, 69, 431, 61);
		panel_3.add(lblNewLabel);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setIcon(new ImageIcon(DefaulterList.class.getResource("/AddNewBookIcons/icons8_Edit_Property_50px.png")));
		lblNewLabel.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 40));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(425, 140, 500, 7);
		panel_3.add(panel_1);
		panel_1.setBackground(Color.RED);
		
		JLabel lblNewLabel_1 = new JLabel("Back");
		lblNewLabel_1.setBounds(0, 0, 128, 48);
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(IssuedBookDetails.class.getResource("/AddNewBookIcons/icons8_Rewind_48px.png")));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 22));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 128, 48);
		panel_3.add(panel_2);
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage home=new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
		panel_2.setBackground(Color.RED);
		panel_2.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 102, 255));
		panel_4.setBounds(0, 773, 1560, 50);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Library Management System");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel_3.setForeground(new Color(255, 255, 0));
		lblNewLabel_3.setBounds(1195, 0, 365, 40);
		panel_4.add(lblNewLabel_3);
	}
}

