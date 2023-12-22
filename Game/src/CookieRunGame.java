import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class CookieRunGame extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int COOKIE_SIZE = 50;
    private static final int HEART_SIZE = 30;
    private static final int OBSTACLE_SIZE = 50;
    private static final int MAX_OBSTACLES = 2;
    private static final int GROUND_HEIGHT = 500;

    private Image cookieImage;
    private Image obstacleImage;
    private Image heartImage;
    private Image backgroundImage;
    private int cookieX, cookieY;
    
    private List<Obstacle> obstacles;
    private List<Heart> hearts;

    private Random random;

    private boolean isJumping;
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveLeft;
    private boolean moveRight;

    private int score;
    private CookieRunPanel panel;
    private boolean penaltyApplied;  // Flag to track if the penalty is applied

    public CookieRunGame() {
        setTitle("Cookie Run Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cookieImage = loadImage("cookie.png", COOKIE_SIZE);
        obstacleImage = loadImage("obstacle.png", OBSTACLE_SIZE);
        heartImage = loadImage("heart.png", HEART_SIZE);
        backgroundImage = loadImage("background.jpg", WIDTH, HEIGHT);

        cookieX = 50;
        cookieY = GROUND_HEIGHT - COOKIE_SIZE;

        obstacles = new ArrayList<>();
        hearts = new ArrayList<>();
        random = new Random();
        isJumping = false;

        score = 0;
        penaltyApplied = false;  

        panel = new CookieRunPanel();
        panel.setLayout(null);
        add(panel);
        setFocusable(true);

        initScoreLabel();

        
        Thread gameLoopThread = new Thread(new GameLoop());
        gameLoopThread.start();

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyRelease(e);
            }
        });

        score = 0;
    }

    private Image loadImage(String filename, int size) {
        return new ImageIcon(filename).getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
    }

    private Image loadImage(String filename, int width, int height) {
        return new ImageIcon(filename).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    private void initScoreLabel() {
        JLabel scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setBounds(10, 10, 100, 30);
        panel.add(scoreLabel);
    }

    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE && !isJumping) {
            jump();
        } else if (keyCode == KeyEvent.VK_LEFT) {
            moveLeft = true;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moveRight = true;
        } else if (keyCode == KeyEvent.VK_UP) {
            moveUp = true;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moveDown = true;
        }
    }

    private void handleKeyRelease(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            moveLeft = false;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moveRight = false;
        } else if (keyCode == KeyEvent.VK_UP) {
            moveUp = false;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moveDown = false;
        }
    }

    private void jump() {
        isJumping = true;
        Timer jumpTimer = new Timer(20, new ActionListener() {
            double velocity = -5;

            @Override
            public void actionPerformed(ActionEvent e) {
                cookieY += velocity;
                velocity += 0.5;

                if (cookieY >= GROUND_HEIGHT - COOKIE_SIZE) {
                    cookieY = GROUND_HEIGHT - COOKIE_SIZE;
                    isJumping = false;
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        jumpTimer.start();
    }

    private void moveCookie() {
        if (moveLeft && cookieX > 0) {
            cookieX -= 5;
        }
        if (moveRight && cookieX < WIDTH - COOKIE_SIZE) {
            cookieX += 5;
        }
        if (moveUp && cookieY > 0) {
            cookieY -= 5;
        }
        if (moveDown && cookieY < GROUND_HEIGHT - COOKIE_SIZE) {
            cookieY += 5;
        }
    }

    private void moveObstacles() {
        if (obstacles.size() < MAX_OBSTACLES && random.nextInt(100) < 5) {
            int obstacleX = WIDTH;
            int obstacleY = GROUND_HEIGHT - OBSTACLE_SIZE - random.nextInt(100);
            obstacles.add(new Obstacle(obstacleX, obstacleY));
        }

        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            obstacle.move();
            if (obstacle.getX() + OBSTACLE_SIZE < 0) {
                iterator.remove();
            }
        }
    }

    private void checkCollision() {
        Rectangle cookieBounds = new Rectangle(cookieX, cookieY, COOKIE_SIZE, COOKIE_SIZE);

        for (Obstacle obstacle : obstacles) {
            Rectangle obstacleBounds = new Rectangle(obstacle.getX(), obstacle.getY(), OBSTACLE_SIZE, OBSTACLE_SIZE);

            if (cookieBounds.intersects(obstacleBounds) && !penaltyApplied) {
                score -= 10;

                if (score < -1) {
                    handleGameOver();
                }
            }
        }

        Iterator<Heart> iterator = hearts.iterator();
        while (iterator.hasNext()) {
            Heart heart = iterator.next();
            Rectangle heartBounds = new Rectangle(heart.getX(), heart.getY(), HEART_SIZE, HEART_SIZE);
            if (cookieBounds.intersects(heartBounds)) {
                iterator.remove();
                score += 10;
            }
        }

        if (score == -10) {
            handleGameOver();
        }
    }

    private void handleGameOver() {
        penaltyApplied = false;  
        int option = JOptionPane.showConfirmDialog(
                this,
                "Game over! Score: " + score + "\nDo you want to play again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void generateHearts() {
        if (random.nextInt(100) < 5 && hearts.size() < 5) {
            int numberOfHearts = random.nextInt(2) + 1;
            for (int i = 0; i < numberOfHearts; i++) {
                int heartX = WIDTH;
                int heartY = GROUND_HEIGHT - HEART_SIZE - random.nextInt(50);
                int spread = random.nextInt(300) - 25;
                hearts.add(new Heart(heartX, heartY - spread));
            }
        }
    }

    private void moveHearts() {
        Iterator<Heart> iterator = hearts.iterator();
        while (iterator.hasNext()) {
            Heart heart = iterator.next();
            heart.moveSpread();
            if (heart.getX() + HEART_SIZE < 0) {
                iterator.remove();
            }
        }
    }

    private void updateScore() {
        for (int i = 0; i < panel.getComponentCount(); i++) {
            if (panel.getComponent(i) instanceof JLabel) {
                JLabel label = (JLabel) panel.getComponent(i);
                if (label.getText().startsWith("Score: ")) {
                    label.setText("Score: " + score);
                    break;
                }
            }
        }
    }

    private void resetGame() {
        penaltyApplied = false;  

        cookieX = 50;
        cookieY = GROUND_HEIGHT - COOKIE_SIZE;

        obstacles.clear();
        hearts.clear();
        random = new Random();
        isJumping = false;

        score = 0;

        panel.repaint();
    }

    private class Obstacle {
        private int x;
        private int y;
        private int speed;

        public Obstacle(int x, int y) {
            this.x = x;
            this.y = y;
            this.speed = new Random().nextInt(5) + 1;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void move() {
            x -= speed;
            y += new Random().nextInt(10) - 5;
            y = Math.min(Math.max(y, 0), GROUND_HEIGHT - OBSTACLE_SIZE);
        }
    }

    private class Heart {
        private int x;
        private int y;
        private int speed;

        public Heart(int x, int y) {
            this.x = x;
            this.y = y;
            this.speed = new Random().nextInt(3) + 1;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void moveSpread() {
            x -= speed;
        }
    }

    private class CookieRunPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawImage(g, backgroundImage, 0, 0, this);
            drawGame(g);
        }
    }

    private void drawGame(Graphics g) {
        for (Obstacle obstacle : obstacles) {
            drawImage(g, obstacleImage, obstacle.getX(), obstacle.getY(), panel);
        }

        for (Heart heart : hearts) {
            drawImage(g, heartImage, heart.getX(), heart.getY(), panel);
        }

        drawImage(g, cookieImage, cookieX, Math.min(cookieY, GROUND_HEIGHT - COOKIE_SIZE), panel);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + score, 10, 30);
    }

    private void drawImage(Graphics g, Image image, int x, int y, JPanel observer) {
        g.drawImage(image, x, y, observer);
    }

    private class GameLoop implements Runnable {
        @Override
        public void run() {
            while (true) {
                moveObstacles();
                checkCollision();
                moveCookie();
                updateScore();
                generateHearts();
                moveHearts();
                panel.repaint();

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CookieRunGame().setVisible(true));
    }
}
