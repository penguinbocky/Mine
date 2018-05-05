package clockpack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Calendar;
import javax.swing.JPanel;

/* The following class is used to  create a clock panel model including a clock's hour/minute/second 
 * with different colors. */
class ClockPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int hour;
	private int minute;
	private int second;

/* When an instance is created, set the panel's background and draw the initial time. */
	public ClockPanel(){
		this.setBackground(new Color(39, 72, 98));
		setCurrentTime();
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);//必须先调用父类绘画方法
		
		int xcenter = this.getWidth() / 2;
		int ycenter = this.getHeight() / 2;
		int clockRadius = (int)Math.min(this.getWidth(), this.getHeight() * 0.4);

		/* Draw a circle and four time points with the white color. */
		g.setColor(new Color(230, 0, 18));
		g.drawOval(xcenter - clockRadius, ycenter - clockRadius, 2 * clockRadius, 2 * clockRadius);
		g.drawOval(xcenter - clockRadius, ycenter - clockRadius, 2 * clockRadius + 1, 2 * clockRadius + 1);
		g.setColor(Color.WHITE);
		g.setFont(new Font("黑体", Font.PLAIN, 18));
		g.drawString("12", xcenter - 6, ycenter - clockRadius + 15);
		g.drawString("3", xcenter + clockRadius - 10, ycenter);
		g.drawString("6", xcenter - 2, ycenter + clockRadius - 4);
		g.drawString("9", xcenter - clockRadius + 4, ycenter);
		/* Draw the second pointer with green color. */		
		g.setColor(Color.WHITE);
		int secLength = (int)(clockRadius * 0.8);
		int secondX = (int)(xcenter + (secLength * (Math.sin(2 * Math.PI * second / 60))));
		int secondY = (int)(ycenter - (secLength * (Math.cos(2 * Math.PI * second / 60))));
		g.drawLine(xcenter, ycenter, secondX, secondY);
		/* Draw the minute pointer with blue color. */
		g.setColor(Color.YELLOW);
		int minLength = (int)(clockRadius * 0.7);
		int minuteX = (int)(xcenter + (minLength * (Math.sin(2 * Math.PI * minute / 60))));
		int minuteY = (int)(ycenter - (minLength * (Math.cos(2 * Math.PI * minute / 60))));
		g.drawLine(xcenter, ycenter, minuteX, minuteY);

		/* Draw the hour pointer with red color. */
		g.setColor(Color.RED);
		int hourLength = (int)(clockRadius * 0.6);
		int hourX = (int)(xcenter + (hourLength * (Math.sin(2 * Math.PI * hour / 12))));
		int hourY = (int)(ycenter - (hourLength * (Math.cos(2 * Math.PI * hour / 12))));
		g.drawLine(xcenter, ycenter, hourX, hourY);
		
		//Add my own mark!
		g.setColor(Color.GRAY);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		g.drawString("made by bocky", xcenter - 60, ycenter + clockRadius - 40);
	}

	/* Get real time for each variable to be referenced. */
	public void setCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		this.hour = calendar.get(Calendar.HOUR);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
	}
	
	public Dimension getPreferredSize(){
        return new Dimension(300,300);
    }
}