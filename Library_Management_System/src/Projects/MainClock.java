package Projects;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainClock extends JFrame{
	
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
	public MainClock(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("MY CLOCK PROGRAM");
		this.setLayout(new FlowLayout());
		this.setSize(350,200);
		this.setResizable(false);
		
		timeformat =new SimpleDateFormat("hh:mm:ss a");
		dayformat =new SimpleDateFormat("EEEE");
		dateformat =new SimpleDateFormat("MMMMM dd, YYYY");
		
		timelabel=new JLabel();
//		time =timeformat.format(Calendar.getInstance().getTime());
//		timelabel.setText(time);
		timelabel.setFont(new Font("Verdana",Font.PLAIN,50));
		timelabel.setForeground(Color.PINK);
		timelabel.setBackground(Color.black);
		timelabel.setOpaque(true);
		
		
		daylabel=new JLabel();
		daylabel.setFont(new Font("Ink Free",Font.PLAIN,45));
		datelabel=new JLabel();
		datelabel.setFont(new Font("Ink Free",Font.PLAIN,35));
		
		daylabel.setForeground(Color.BLUE);
		daylabel.setBackground(Color.WHITE);
		datelabel.setForeground(Color.ORANGE);
		datelabel.setBackground(Color.WHITE);
		
		this.add(timelabel);
		this.add(daylabel);
		this.add(datelabel);
		this.setVisible(true);
		
		settime();
	}
	
	public void settime() {
		while(true) {
			time =timeformat.format(Calendar.getInstance().getTime());
			timelabel.setText(time);
			
			day =dayformat.format(Calendar.getInstance().getTime());
			daylabel.setText(day);
			
			date =dateformat.format(Calendar.getInstance().getTime());
			datelabel.setText(date);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
