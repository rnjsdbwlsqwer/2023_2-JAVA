import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class CarGameTest extends JFrame {
	BufferedImage img = null;
	int img_x = 100, img_y = 30;

	class MyPanel extends JPanel {

		public MyPanel() {
			try {
				img = ImageIO.read(new File("c:\\car.png"));

			} catch (IOException e) {
				System.out.println("no image");
				System.exit(1);
			}
			addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent e) {
					int keycode = e.getKeyCode();
					switch (keycode) {
					case KeyEvent.VK_UP:	img_y -= 10;	break;
					case KeyEvent.VK_DOWN:	img_y += 10;	break;
					case KeyEvent.VK_LEFT:	img_x -= 10;	break;
					case KeyEvent.VK_RIGHT:	img_x += 10;	break;
					}
					repaint();
				}
				public void keyReleased(KeyEvent arg0) {		}
				public void keyTyped(KeyEvent arg0) {			}

			});
			this.requestFocus();
			setFocusable(true);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, img_x, img_y, null);

		}
	}

	public CarGameTest() {
		setSize(350, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new MyPanel());
		JPanel p = new JPanel();
		JButton b1 = new JButton("LEFT");
		b1.addActionListener(e->{
			img_x -= 10; 
			repaint(); //강제로 컴포넌트를 다시 그리게함 
		});
		JButton b2 = new JButton("RIGHT");
		b2.addActionListener(e->{
			img_x += 10;
			repaint(); 
		});
		p.add(b1);
		p.add(b2);
		add(p, "South");
		setVisible(true);
	}

	public static void main(String[] args) {
		CarGameTest s = new CarGameTest();
	}
}