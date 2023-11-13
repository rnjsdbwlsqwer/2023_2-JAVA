import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CountDownTest extends JFrame {
    private JLabel label;
    private JButton startButton;
    private JButton stopButton;
    
    private Counter counter;
    
    class Counter extends Thread {
        private volatile boolean isRunning = true;

        public void stopCounting() {
            isRunning = false;
        }

        public void run() {
            for (int i = 0; i <= 100 && isRunning; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
                label.setText(i + "");
            }
        }
    }

    public CountDownTest() {
        setTitle("카운트다운");
        setSize(400, 200);
        getContentPane().setLayout(null);

        label = new JLabel("0");
        label.setBounds(0, 0, 384, 111);
        label.setFont(new Font("Serif", Font.BOLD, 100));
        getContentPane().add(label);

        startButton = new JButton("시작");
        startButton.setBounds(10, 120, 150, 30);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startCountdown();
            }
        });
        getContentPane().add(startButton);

        stopButton = new JButton("중지");
        stopButton.setBounds(190, 120, 150, 30);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopCountdown();
            }
        });
        getContentPane().add(stopButton);
    }

    private void startCountdown() {
        counter = new Counter();
        counter.start();
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
    }

    private void stopCountdown() {
        if (counter != null) {
            counter.stopCounting();
            counter.interrupt();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        CountDownTest t = new CountDownTest();
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setVisible(true);
    }
}
