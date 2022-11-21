package jframe;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Window;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import Projects.MainClock;
import Projects.SnakeFrame;
import Projects.TICTACTOE;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import rojerusan.RSButtonMetro;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import rojerusan.RSButtonPane;

public class Additions extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	Calendar cal;
	SimpleDateFormat timeformat;
	SimpleDateFormat dayformat;
	SimpleDateFormat dateformat;
	JLabel timelabel;
	JLabel daylabel;
	JLabel datelabel;
	String day;
	String time;
	String date;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Additions frame = new Additions();
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
	public Additions() {
		setUndecorated(true);
		setLocation(new Point(1532, 832));
		setSize(new Dimension(1532, 832));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(100, 100, 1523, 844);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(-51, 10, 1523, 844);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 7, 4, (Color) new Color(255, 0, 255)));
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(370, 488, 531, 322);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Snake And Apples");
		lblNewLabel.setBounds(25, 263, 333, 47);
		lblNewLabel.setFont(new Font("Rockwell Extra Bold", Font.ITALIC, 30));
		lblNewLabel.setForeground(new Color(0, 0, 102));
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Additions.class.getResource("/icons/snake.png")));
		lblNewLabel_1.setBounds(0, 0, 518, 259);
		panel_1.add(lblNewLabel_1);
		
		RSButtonMetro btnmtrPlay = new RSButtonMetro();
		btnmtrPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Projects.SnakeFrame books=new Projects.SnakeFrame();
				
				books.setVisible(true);
				dispose();
			}
		});
		btnmtrPlay.setBounds(368, 262, 150, 50);
		panel_1.add(btnmtrPlay);
		btnmtrPlay.setFont(new Font("Verdana", Font.BOLD, 25));
		btnmtrPlay.setText("PLAY");
		
		JPanel panel_3_1_1_2 = new JPanel();
		panel_3_1_1_2.setBackground(new Color(0, 0, 204));
		panel_3_1_1_2.setBounds(18, 305, 350, 7);
		panel_1.add(panel_3_1_1_2);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new MatteBorder(1, 1, 7, 4, (Color) new Color(255, 0, 255)));
		panel_1_1.setBackground(new Color(240, 248, 255));
		panel_1_1.setBounds(900, 40, 679, 405);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JPanel panel_3_1_1 = new JPanel();
		panel_3_1_1.setBackground(new Color(0, 0, 204));
		panel_3_1_1.setBounds(47, 388, 350, 7);
		panel_1_1.add(panel_3_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Ping Pong Game");
		lblNewLabel_2_1.setForeground(new Color(0, 0, 102));
		lblNewLabel_2_1.setFont(new Font("Rockwell Extra Bold", Font.ITALIC, 30));
		lblNewLabel_2_1.setBounds(47, 346, 333, 47);
		panel_1_1.add(lblNewLabel_2_1);
		
		RSButtonMetro btnmtrPlay_1_1 = new RSButtonMetro();
		btnmtrPlay_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Projects.GameFrame books=new Projects.GameFrame();
				books.setVisible(true);
				dispose();
			}
		});
		btnmtrPlay_1_1.setText("PLAY");
		btnmtrPlay_1_1.setFont(new Font("Verdana", Font.BOLD, 25));
		btnmtrPlay_1_1.setBounds(426, 345, 150, 50);
		panel_1_1.add(btnmtrPlay_1_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(Additions.class.getResource("/icons/PINGPONG (2).jpg")));
		lblNewLabel_3_1.setBounds(10, 10, 594, 328);
		panel_1_1.add(lblNewLabel_3_1);
		
		JPanel panel_1_3 = new JPanel();
		panel_1_3.setBorder(new MatteBorder(1, 1, 7, 4, (Color) new Color(255, 0, 255)));
		panel_1_3.setBackground(new Color(240, 248, 255));
		panel_1_3.setBounds(900, 488, 623, 322);
		panel.add(panel_1_3);
		panel_1_3.setLayout(null);
		
		RSButtonMetro btnmtrPlay_1_1_1 = new RSButtonMetro();
		btnmtrPlay_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				project.GUI window=new project.GUI();
				window.frame.setVisible(true);
				dispose();
				
			}
		});
		btnmtrPlay_1_1_1.setText("PLAY");
		btnmtrPlay_1_1_1.setFont(new Font("Verdana", Font.BOLD, 25));
		btnmtrPlay_1_1_1.setBounds(438, 263, 150, 50);
		panel_1_3.add(btnmtrPlay_1_1_1);
		
		JLabel lblNewLabel_6 = new JLabel("\r\n");
		lblNewLabel_6.setIcon(new ImageIcon(Additions.class.getResource("/icons/sps.jpg")));
		lblNewLabel_6.setBounds(53, 42, 324, 271);
		panel_1_3.add(lblNewLabel_6);
		
		JPanel panel_3_1_1_1 = new JPanel();
		panel_3_1_1_1.setBounds(404, 20, 7, 300);
		panel_1_3.add(panel_3_1_1_1);
		panel_3_1_1_1.setBackground(new Color(0, 0, 204));
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Stone");
		lblNewLabel_2_1_1.setForeground(new Color(0, 0, 102));
		lblNewLabel_2_1_1.setFont(new Font("Rockwell Extra Bold", Font.ITALIC, 30));
		lblNewLabel_2_1_1.setBounds(463, 64, 108, 47);
		panel_1_3.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Paper");
		lblNewLabel_2_1_1_1.setForeground(new Color(0, 0, 102));
		lblNewLabel_2_1_1_1.setFont(new Font("Rockwell Extra Bold", Font.ITALIC, 30));
		lblNewLabel_2_1_1_1.setBounds(460, 106, 128, 47);
		panel_1_3.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("And");
		lblNewLabel_2_1_1_2.setForeground(new Color(0, 0, 102));
		lblNewLabel_2_1_1_2.setFont(new Font("Rockwell Extra Bold", Font.ITALIC, 30));
		lblNewLabel_2_1_1_2.setBounds(480, 152, 108, 47);
		panel_1_3.add(lblNewLabel_2_1_1_2);
		
		JLabel lblNewLabel_2_1_1_3 = new JLabel("Scissor");
		lblNewLabel_2_1_1_3.setForeground(new Color(0, 0, 102));
		lblNewLabel_2_1_1_3.setFont(new Font("Rockwell Extra Bold", Font.ITALIC, 30));
		lblNewLabel_2_1_1_3.setBounds(448, 192, 150, 47);
		panel_1_3.add(lblNewLabel_2_1_1_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 4, 5, 1, (Color) new Color(0, 0, 204)));
		panel_2.setBackground(new Color(255, 255, 102));
		panel_2.setBounds(53, 0, 318, 834);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Additions.class.getResource("/icons/CALC (4).jpg")));
		lblNewLabel_4.setBounds(24, 60, 272, 646);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Calculator");
		lblNewLabel_5.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
		lblNewLabel_5.setForeground(new Color(0, 0, 153));
		lblNewLabel_5.setBounds(65, 703, 171, 44);
		panel_2.add(lblNewLabel_5);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBackground(new Color(0, 0, 204));
		panel_3_2.setBounds(37, 747, 225, 7);
		panel_2.add(panel_3_2);
		
		RSButtonMetro btnmtrUse = new RSButtonMetro();
		btnmtrUse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				Projects.GameFrame books=new Projects.GameFrame();
				Projects.calculator window=new Projects.calculator();
				window.frame.setVisible(true);
//				obj.setVisible(true);
				dispose();
			}
		});
		btnmtrUse.setText("USE");
		btnmtrUse.setFont(new Font("Verdana", Font.BOLD, 25));
		btnmtrUse.setBounds(74, 764, 150, 50);
		panel_2.add(btnmtrUse);
		
		RSButtonMetro btnmtrBack = new RSButtonMetro();
		btnmtrBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage home=new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
		btnmtrBack.setBackground(Color.RED);
		btnmtrBack.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnmtrBack.setText("Back");
		btnmtrBack.setBounds(23, 10, 109, 32);
		panel_2.add(btnmtrBack);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBounds(370, 40, 531, 405);
		panel.add(panel_1_2);
		panel_1_2.setBorder(new MatteBorder(1, 1, 7, 4, (Color) new Color(255, 0, 255)));
		panel_1_2.setBackground(new Color(224, 255, 255));
		panel_1_2.setLayout(null);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(new Color(0, 0, 204));
		panel_3_1.setBounds(29, 375, 280, 7);
		panel_1_2.add(panel_3_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tic Tac Toe");
		lblNewLabel_2.setForeground(new Color(0, 0, 102));
		lblNewLabel_2.setFont(new Font("Rockwell Extra Bold", Font.ITALIC, 30));
		lblNewLabel_2.setBounds(50, 329, 293, 47);
		panel_1_2.add(lblNewLabel_2);
		
		RSButtonMetro btnmtrPlay_1 = new RSButtonMetro();
		btnmtrPlay_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Projects.TICTACTOE books=new Projects.TICTACTOE();
//				books.setVisible(true);
				dispose();
			}
		});
		btnmtrPlay_1.setText("PLAY");
		btnmtrPlay_1.setFont(new Font("Verdana", Font.BOLD, 25));
		btnmtrPlay_1.setBounds(353, 339, 150, 50);
		panel_1_2.add(btnmtrPlay_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Additions.class.getResource("/icons/tictac.jpeg")));
		lblNewLabel_3.setBounds(29, 10, 447, 309);
		panel_1_2.add(lblNewLabel_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.RED);
		panel_3.setBounds(345, 444, 1216, 44);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Single Player Games");
		lblNewLabel_7.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_7.setForeground(Color.CYAN);
		lblNewLabel_7.setBounds(351, 5, 407, 29);
		panel_3.add(lblNewLabel_7);
		
		JPanel panel_3_3 = new JPanel();
		panel_3_3.setLayout(null);
		panel_3_3.setBackground(Color.RED);
		panel_3_3.setBounds(370, 0, 1153, 44);
		panel.add(panel_3_3);
		
		JLabel lblNewLabel_7_1 = new JLabel("MultiPlayers  Games");
		lblNewLabel_7_1.setForeground(Color.CYAN);
		lblNewLabel_7_1.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel_7_1.setBounds(324, 0, 451, 42);
		panel_3_3.add(lblNewLabel_7_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.BLACK);
		panel_4.setBounds(1472, 10, 51, 844);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("P");
		lblNewLabel_8.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setBounds(10, 201, 41, 36);
		panel_4.add(lblNewLabel_8);
		
		JLabel lblNewLabel_8_1 = new JLabel("L");
		lblNewLabel_8_1.setForeground(Color.WHITE);
		lblNewLabel_8_1.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_8_1.setBounds(10, 247, 41, 36);
		panel_4.add(lblNewLabel_8_1);
		
		JLabel lblNewLabel_8_2 = new JLabel("A");
		lblNewLabel_8_2.setForeground(Color.WHITE);
		lblNewLabel_8_2.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_8_2.setBounds(10, 293, 41, 36);
		panel_4.add(lblNewLabel_8_2);
		
		JLabel lblNewLabel_8_3 = new JLabel("Y");
		lblNewLabel_8_3.setForeground(Color.WHITE);
		lblNewLabel_8_3.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_8_3.setBounds(10, 339, 41, 36);
		panel_4.add(lblNewLabel_8_3);
		
		JLabel lblNewLabel_8_4 = new JLabel("A");
		lblNewLabel_8_4.setForeground(Color.WHITE);
		lblNewLabel_8_4.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_8_4.setBounds(10, 498, 41, 36);
		panel_4.add(lblNewLabel_8_4);
		
		JLabel lblNewLabel_8_5 = new JLabel("G");
		lblNewLabel_8_5.setForeground(Color.WHITE);
		lblNewLabel_8_5.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_8_5.setBounds(10, 452, 41, 36);
		panel_4.add(lblNewLabel_8_5);
		
		JLabel lblNewLabel_8_6 = new JLabel("M");
		lblNewLabel_8_6.setForeground(Color.WHITE);
		lblNewLabel_8_6.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_8_6.setBounds(0, 544, 41, 36);
		panel_4.add(lblNewLabel_8_6);
		
		JLabel lblNewLabel_8_7 = new JLabel("E");
		lblNewLabel_8_7.setForeground(Color.WHITE);
		lblNewLabel_8_7.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_8_7.setBounds(10, 590, 41, 36);
		panel_4.add(lblNewLabel_8_7);
		
		JLabel lblNewLabel_8_8 = new JLabel("S");
		lblNewLabel_8_8.setForeground(Color.WHITE);
		lblNewLabel_8_8.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_8_8.setBounds(10, 635, 41, 36);
		panel_4.add(lblNewLabel_8_8);
		
		
		timeformat =new SimpleDateFormat("hh:mm:ss a");
		dayformat =new SimpleDateFormat("EEEE");
		dateformat =new SimpleDateFormat("MMMMM dd, YYYY");
		
	}
}
