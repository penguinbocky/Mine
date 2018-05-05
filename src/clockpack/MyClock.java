/************************************************************************************
 * This java file is used for a test to make a clock which can display the real time.
 * It mainly includes four following function parts:
 * 			1. Draw a clock panel containing a circle frame and four time numbers
 * 			2. Draw three time pointer:second/minute/hour
 * 			3. Get real second/minute/hour and save them
 * 			4. Add a listener to respond to the event which repaint the clock panel
 * 
 * Author:Bocky					2015/07/24
 ************************************************************************************/

package clockpack;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;

public class MyClock extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Create an instance of ClockPanel. */
	private ClockPanel clkp = new ClockPanel();
	
	private JTextField lcd = new JTextField();
	private Calendar calendar;
	
	public MyClock(){
		this.setLayout(new BorderLayout());
		this.add(clkp, BorderLayout.NORTH);
		
		JPanel lcdP  = new JPanel();
		JButton btnClose = new JButton("¹Ø  ±Õ");
		lcd.setBackground(new Color(229, 187, 129));
		lcd.setForeground(new Color(161, 23, 21));
		lcd.setFocusable(false);
		lcd.setBorder(null);
		lcd.setEditable(false);
		
		calendar = Calendar.getInstance();
		lcd.setText(" " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" 
		+ calendar.get(Calendar.SECOND) + "  --" + "Copyright (C)    ");
		btnClose.addActionListener(this);
		btnClose.setOpaque(false);
		btnClose.setFont(new Font("ËÎÌå", Font.PLAIN, 14));
		lcdP.add(lcd, "Center");
		lcdP.add(btnClose, "East");
		lcdP.setBackground(new Color(229, 187, 129));
		this.add(lcdP, BorderLayout.CENTER);
		
		/* Create an instance of Timer and set its delay and its listener.*/
		Timer timer = new Timer(1000, new TimerListener());
		timer.start();
		
		this.setTitle("MyClock");
		this.setLocation(450, 200);
//		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		Point mouseOrigin = new Point();
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				mouseOrigin.x = e.getX();
				mouseOrigin.y = e.getY();
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				MyClock.this.setLocation(e.getXOnScreen() - mouseOrigin.x, 
									e.getYOnScreen() - mouseOrigin.y);
			}
		});
//		this.pack();
		this.setSize(320, 340);
//		this.setResizable(false);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private class TimerListener implements ActionListener{
		//Every time(1s) the TimerListener is executed, the current real time is got 
		//and the clock panel is repainted.
		public void actionPerformed(ActionEvent e){
			clkp.setCurrentTime();
			clkp.repaint();
			
			calendar = Calendar.getInstance();
			lcd.setText(" " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" 
					+ calendar.get(Calendar.SECOND) + "  --" + "Copyright (C)    ");
		}
	}
	
	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new MyClock();
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}


