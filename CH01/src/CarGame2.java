import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class CarGame2 extends JFrame {
	class MyThread extends Thread{
		private JLabel label;
		private int x, y;
		
		
		public MyThread(String fname, int x, int y) {
			this.x = x;
			this.y = y;
			label = new JLabel();
			label.setIcon(new ImageIcon(fname));
			label.setBounds(x,y,100,200);
			
			add(label);
		}
		
		
		public void run() {
			for(int i =0;i<200;i++) {
				x += 10 * Math.random();
				SwingUtilities.invokeLater(()->label.setBounds(x,y,100,100));
				
				if(x >= 600) {
					
					announceWinner(label);
					break;
				}
				try {
					Thread.sleep(100);
					
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public CarGame2() {
		setTitle("CarRace");
		setSize(800,400);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
	
		JLabel label2 = new JLabel();
		label2.setIcon(new ImageIcon("race.gif"));
		label2.setBounds(600,50,100,100);
		
		add(label2);


		
		
		(new MyThread("car1.gif", 100, 0)).start();
		(new MyThread("car2.gif", 100, 50)).start();
		(new MyThread("car3.gif", 100, 100)).start();
		(new MyThread("car1.gif", 100, 150)).start();
		(new MyThread("car2.gif", 100, 200)).start();
		
		
		setVisible(true);
	}
	private void announceWinner(JLabel winnerLabel) {
		System.out.println(winnerLabel.getIcon().toString() + "이 우승했습니다.");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater (CarGame2 :: new);
	}

}

