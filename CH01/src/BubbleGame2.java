
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BubbleGame2 extends JFrame {

	private Timer timer;

    public BubbleGame2() {
        setTitle("버블 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel p = new GamePanel();  
        setSize(500, 500);
        setContentPane(p);
        JButton button1 = new JButton("시작");
        JButton button2 = new JButton("종료");
        
        button1.setBounds(100,300,100,100);
        button2.setBounds(300,300,100,100);
        
        button1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		startCountdown();
        	}
        });
        
        button2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		stopCountdown();
        	}
        });
        
        

        add(button1);
        add(button2);
        

        
        setVisible(true);
    }

    class GamePanel extends JPanel {
    public GamePanel() {
        setLayout(null);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                new BubbleThread(e.getX(), e.getY(), GamePanel.this).start();
            }
        });
    }
}

private void startCountdown() {
	
	
	
    if (timer != null && timer.isRunning()) {
        timer.stop();
    }

    timer.start();
}

private void stopCountdown() {
    if (timer != null && timer.isRunning()) {
        timer.stop();
    }
}
    
    
    
    public static void main(String[] args) {
        new BubbleGame2();
    }
}



class BubbleThread extends Thread {
    private JLabel bubble;
    private Timer timer;
    private JPanel panel;

    public BubbleThread(int bubbleX, int bubbleY, JPanel panel) {
        this.panel = panel;

        ImageIcon img = new ImageIcon("bubble.jpg");
        bubble = new JLabel(img);
        bubble.setSize(img.getIconWidth(), img.getIconHeight());
        bubble.setLocation(bubbleX, bubbleY);

        panel.add(bubble);
        panel.repaint();

        timer = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveBubble();
            }
        });
        timer.start();
    }

    private void moveBubble() {
        int newY = bubble.getY() - 5;
        bubble.setLocation(bubble.getX(), newY);

        if (newY + bubble.getHeight() < 0 || newY > panel.getHeight()) {
            // Bubble is completely out of the frame, so exit the program
            System.exit(0);
        }
    }
}
