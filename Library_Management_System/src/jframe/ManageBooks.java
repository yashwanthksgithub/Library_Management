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
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import rojerusan.RSLabelImage;
import javax.swing.JTextPane;
import rojerusan.RSMaterialButtonRectangle;
import rojerusan.RSMetroTextPlaceHolderBeanInfo;
import rojerusan.RSMaterialButtonRectangleBeanInfo;
import rojerusan.RSMaterialButtonCircleBeanInfo;
import javax.swing.JScrollPane;
import rojeru_san.complementos.RSTableMetro;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
public class ManageBooks extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageBooks frame = new ManageBooks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	RSTableMetro tbl_bookDetails_1;
	String book_name,author;
	int  book_id,quantity;
	 DefaultTableModel model;
	 
	 JTextPane textPane;
	 JTextPane textPane_1;
	 JTextPane textPane_2;
	 JTextPane textPane_3;
	 
	 private final Action action = new SwingAction();
	 private final Action action_1 = new SwingAction_1();
	 private final Action action_2 = new SwingAction_2();
	 private final Action action_3 = new SwingAction_3();
//	 private JTable table;
	
	 public void setbookdetalis() {
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","Yashu@123");
				Statement sr=con.createStatement();
				String query="select * from book_details";
				ResultSet rs=sr.executeQuery(query);
				
				ResultSetMetaData rsmd=rs.getMetaData();
				
				DefaultTableModel model = (DefaultTableModel)tbl_bookDetails_1.getModel();
				
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
	 
	 
	 public boolean addBook() {
		 boolean added=false;
		 book_id=Integer.parseInt(textPane.getText());
		 book_name=textPane_1.getText();
		 author=textPane_2.getText();
		 quantity=Integer.parseInt(textPane_3.getText());
		 
		 try {
			 Connection con=DBConnection.getConnection();
			 String sql="insert into book_details values(?,?,?,?)";
			 PreparedStatement pt=con.prepareStatement(sql);
			 pt.setInt(1, book_id);
			 pt.setString(2, book_name);
			 pt.setString(3, author);
			 pt.setInt(4, quantity);
			 
			 
			 int rowCount=pt.executeUpdate();
			 if(rowCount>0) {
				 added=true;
			 }else {
				 added=false;
			 }
			 return added;
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 return added;
	 }
	 
	 
	 public void clearTable() {
			DefaultTableModel model = (DefaultTableModel)tbl_bookDetails_1.getModel();
			model.setRowCount(0);
	 }
	 
	 
	 public void clearstudent() {
			textPane.setText("");
			textPane_1.setText("");
			textPane_2.setText("");
			textPane_3.setText("");
			
		}
	 
	 
	 public boolean updatebook() {
		 boolean updated=false;
		 book_id=Integer.parseInt(textPane.getText());
		 book_name=textPane_1.getText();
		 author=textPane_2.getText();
		 quantity=Integer.parseInt(textPane_3.getText());
		 
		 try {
			 
			 Connection con=DBConnection.getConnection();
			 String sql="update book_details set book_name=?,author=?,quantity=? where book_id=?";
			 PreparedStatement pt=con.prepareStatement(sql);
			 pt.setInt(4, book_id);
			 pt.setString(1, book_name);
			 pt.setString(2, author);
			 pt.setInt(3, quantity);
			 int rowCount=pt.executeUpdate();
			 if(rowCount>0) {
				 updated=true;
			 }else {
				 updated=false;
			 }
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 return updated;
	 }
	 
	 
	 public boolean deletebook() {
		 boolean delete=false;
		 book_id=Integer.parseInt(textPane.getText());
		 try {
			 Connection con=DBConnection.getConnection();
			 String sql="delete from book_details where book_id = ?";
			 PreparedStatement pt=con.prepareStatement(sql);
			 pt.setInt(1, book_id);
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
	 
	 
	 
	
	/**
	 * Create the frame.
	 */
	public ManageBooks() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1724, 824);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 51, 204));
		panel.setBounds(0, -15, 580, 801);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 10, 188, 55);
		panel.add(panel_1);
		panel_1.setBackground(new Color(255, 0, 0));
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BACK");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage home=new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
		
		lblNewLabel.setBounds(26, 10, 121, 41);
		panel_1.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(ManageBooks.class.getResource("/AddNewBookIcons/icons8_Rewind_48px.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ManageBooks.class.getResource("/AddNewBookIcons/icons8_Moleskine_26px.png")));
		lblNewLabel_1.setBounds(59, 283, 50, 50);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Book_Id");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(119, 128, 95, 36);
		panel.add(lblNewLabel_2);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 28));
		textPane.setBounds(119, 174, 365, 53);
		panel.add(textPane);
		
		JLabel lblNewLabel_2_1 = new JLabel("Enter Book Name");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(119, 237, 210, 36);
		panel.add(lblNewLabel_2_1);
		
		textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 28));
		textPane_1.setBounds(119, 283, 365, 55);
		panel.add(textPane_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Author Name");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(119, 356, 155, 36);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setIcon(new ImageIcon(ManageBooks.class.getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png")));
		lblNewLabel_1_2.setBounds(59, 402, 50, 50);
		panel.add(lblNewLabel_1_2);
		
		textPane_2 = new JTextPane();
		textPane_2.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 28));
		textPane_2.setBounds(119, 402, 359, 50);
		panel.add(textPane_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Quntaity");
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_3.setBounds(119, 478, 95, 36);
		panel.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_1_3 = new JLabel("");
		lblNewLabel_1_3.setIcon(new ImageIcon(ManageBooks.class.getResource("/AddNewBookIcons/icons8_Unit_26px.png")));
		lblNewLabel_1_3.setBounds(59, 524, 50, 50);
		panel.add(lblNewLabel_1_3);
		
		textPane_3 = new JTextPane();
		textPane_3.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 28));
		textPane_3.setBounds(119, 524, 359, 50);
		panel.add(textPane_3);
		
		RSMaterialButtonRectangle mtrlbtnrctnglAdd = new RSMaterialButtonRectangle();
		mtrlbtnrctnglAdd.setAction(action_1);
		mtrlbtnrctnglAdd.setBounds(31, 613, 165, 70);
		panel.add(mtrlbtnrctnglAdd);
		mtrlbtnrctnglAdd.setFont(new Font("Dubai", Font.BOLD, 34));
		mtrlbtnrctnglAdd.setForeground(Color.BLACK);
		mtrlbtnrctnglAdd.setText("ADD");
		mtrlbtnrctnglAdd.setBackground(Color.YELLOW);
		
		RSMaterialButtonRectangle mtrlbtnrctnglUpdate = new RSMaterialButtonRectangle();
		mtrlbtnrctnglUpdate.setAction(action_2);
		
		mtrlbtnrctnglUpdate.setText("Update");
		mtrlbtnrctnglUpdate.setForeground(Color.BLACK);
		mtrlbtnrctnglUpdate.setFont(new Font("Dubai", Font.BOLD, 34));
		mtrlbtnrctnglUpdate.setBackground(Color.YELLOW);
		mtrlbtnrctnglUpdate.setBounds(216, 613, 165, 70);
		panel.add(mtrlbtnrctnglUpdate);
		
		RSMaterialButtonRectangle mtrlbtnrctnglDelete = new RSMaterialButtonRectangle();
		mtrlbtnrctnglDelete.setAction(action_3);
		mtrlbtnrctnglDelete.setText("Delete");
		mtrlbtnrctnglDelete.setForeground(Color.BLACK);
		mtrlbtnrctnglDelete.setFont(new Font("Dubai", Font.BOLD, 34));
		mtrlbtnrctnglDelete.setBackground(Color.YELLOW);
		mtrlbtnrctnglDelete.setBounds(405, 613, 165, 70);
		panel.add(mtrlbtnrctnglDelete);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(ManageBooks.class.getResource("/AddNewBookIcons/icons8_Contact_26px.png")));
		lblNewLabel_1_1.setBounds(59, 174, 50, 50);
		panel.add(lblNewLabel_1_1);
		
		RSMaterialButtonRectangle mtrlbtnrctnglClear = new RSMaterialButtonRectangle();
		mtrlbtnrctnglClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearstudent();
			}
		});
		mtrlbtnrctnglClear.setText("Clear");
		mtrlbtnrctnglClear.setForeground(Color.BLACK);
		mtrlbtnrctnglClear.setFont(new Font("Dubai", Font.BOLD, 34));
		mtrlbtnrctnglClear.setBackground(Color.YELLOW);
		mtrlbtnrctnglClear.setBounds(216, 706, 165, 70);
		panel.add(mtrlbtnrctnglClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(581, 0, 1004, 787);
		contentPane.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3_4_1 = new JLabel("Books Details");
		lblNewLabel_3_4_1.setBounds(358, 245, 266, 43);
		lblNewLabel_3_4_1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 30));
		panel_2.add(lblNewLabel_3_4_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setToolTipText("babffdb");
		scrollPane_3.setBounds(128, 298, 720, 289);
		panel_2.add(scrollPane_3);
		
		
		
		 tbl_bookDetails_1 = new RSTableMetro();
		 tbl_bookDetails_1.setColorFilasForeground1(new Color(0, 0, 153));
		 tbl_bookDetails_1.setColorFilasForeground2(new Color(0, 0, 153));
		 tbl_bookDetails_1.setColorFilasBackgound2(Color.WHITE);
		 tbl_bookDetails_1.setColorFilasBackgound1(new Color(255, 255, 255));
		 tbl_bookDetails_1.setColorBackgoundHead(Color.RED);

//		setBookDetailsTotable() ;
		tbl_bookDetails_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int rowNo=tbl_bookDetails_1.getSelectedRow();
				TableModel model=tbl_bookDetails_1.getModel();
				
				textPane.setText(model.getValueAt(rowNo, 0).toString());
				textPane_1.setText(model.getValueAt(rowNo, 1).toString());
				textPane_2.setText(model.getValueAt(rowNo, 2).toString());
				textPane_3.setText(model.getValueAt(rowNo, 3).toString());
			}
		});
		tbl_bookDetails_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));

		scrollPane_3.setViewportView(tbl_bookDetails_1);
		
		JLabel lblNewLabel_3 = new JLabel("Manage Books");
		lblNewLabel_3.setBounds(435, 75, 311, 61);
		lblNewLabel_3.setIcon(new ImageIcon(ManageBooks.class.getResource("/AddNewBookIcons/icons8_Book_50px_1.png")));
		lblNewLabel_3.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBackground(Color.RED);
		panel_2.add(lblNewLabel_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(405, 131, 423, 5);
		panel_3.setBackground(Color.RED);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		RSMaterialButtonRectangle mtrlbtnrctnglDelete_1 = new RSMaterialButtonRectangle();
		mtrlbtnrctnglDelete_1.setAction(action);
		mtrlbtnrctnglDelete_1.setText("View Details");
		mtrlbtnrctnglDelete_1.setForeground(Color.BLACK);
		mtrlbtnrctnglDelete_1.setFont(new Font("Dubai", Font.BOLD, 34));
		mtrlbtnrctnglDelete_1.setBackground(new Color(255, 51, 255));
		mtrlbtnrctnglDelete_1.setBounds(128, 608, 245, 70);
		panel_2.add(mtrlbtnrctnglDelete_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(ManageBooks.class.getResource("/icons/thu.jpg")));
		lblNewLabel_4.setBounds(43, 10, 355, 214);
		panel_2.add(lblNewLabel_4);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 102, 255));
		panel_4.setBounds(0, 779, 1585, 45);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Library Management System");
		lblNewLabel_5.setForeground(new Color(255, 255, 51));
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel_5.setBounds(1171, 0, 381, 38);
		panel_4.add(lblNewLabel_5);
		
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			clearTable();
			setbookdetalis();
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(addBook()==true) {
				clearTable();
				setbookdetalis();
				JOptionPane.showMessageDialog(null, "Book Added");
			}
			else {
				JOptionPane.showMessageDialog(null, "Failed To Add Book");

			}
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "SwingAction_2");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(updatebook()==true) {
				clearTable();
				setbookdetalis();
				JOptionPane.showMessageDialog(null, "Book Updated");
			}
			else {
				JOptionPane.showMessageDialog(null, "Failed To Update Book");

			}
			
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "SwingAction_3");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(deletebook()==true) {
				clearTable();
				setbookdetalis();
				JOptionPane.showMessageDialog(null, "Book Deleted ");
			}
			else {
				JOptionPane.showMessageDialog(null, "Failed To Delete A Book");

			}
		}
	}
}
