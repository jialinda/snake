package org.cis120.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * GameCourt
 *
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 */

public class GameCourt extends JPanel {

    // the state of the game logic
    private Snake snake; // the SNAKE, controlled by the Keyboard
    private Apple apple; // the FOOD, snake wants to eat these
    private Poison poison; // the POISON, snake must avoid this

    private boolean playing = false; // whether the game is running
    private final JLabel status; // Current status text, i.e. "Running..."

    // Game constants
    public static final int COURT_WIDTH = 300;
    public static final int COURT_HEIGHT = 300;

    // Update interval for timer, in milliseconds
    private static int interval = 100;

    public GameCourt(JLabel status) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Timer timer = new Timer(interval, e -> tick());
        timer.start();
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getVx() != -10) {
                    snake.setVx(10);
                    snake.setVy(0);
                    snake.setDirection(2);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.getVx() != 10) {
                    snake.setVx(-10);
                    snake.setVy(0);
                    snake.setDirection(1);
                } else if (e.getKeyCode() == KeyEvent.VK_UP && snake.getVy() != -10) {
                    snake.setVx(0);
                    snake.setVy(10);
                    snake.setDirection(3);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.getVy() != 10) {
                    snake.setVx(0);
                    snake.setVy(-10);
                    snake.setDirection(4);
                }
            }
        });
        this.status = status;
    }

    // GET METHODS
    public int getInterval() {
        return interval;
    }

    // SET METHODS
    public void setInterval(int i) {
        interval = i;
    }

    public void popUp() {
        int generateRanNum = (int) (Math.random() * 30);
        if (snake.body[generateRanNum][generateRanNum] > 0) {
            popUp();
        } else {
            apple.setAppleBoard(generateRanNum, generateRanNum);
            if (snake.getLength() > 5) {
                int ranNum2 = (int) (Math.random() * 30);
                poison.setPoisonBoard(ranNum2, ranNum2);
            }
        }
    }

    /**
     * (Re-)set the game to its initial state.
     */

    public void reset() {
        // RESET ALL THE DIFFERENT COMPONENTS OF THE GAME BACK TO STANDARD
        apple = new Apple(COURT_WIDTH, COURT_HEIGHT, 15, 15);
        snake = new Snake(150, 150, 16, 16, COURT_WIDTH, COURT_HEIGHT);
        poison = new Poison(COURT_WIDTH, COURT_HEIGHT, 0, 0);

        playing = true;
        popUp();
        status.setText("Running...");

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */

    void tick() {
        if (playing) {
            if (snake.intersects(poison)) {
                playing = false;
                status.setText("YOU ATE POISON! Score: " + snake.getLength());
            } else if (snake.intersects(apple)) {
                snake.growLength();
                popUp();
                status.setText("KEEP EATIN'! Current Score: " + (snake.getLength()));
            } else if (snake.collideIntoWall()) {
                playing = false;
                System.out.println("Hit wall");
                status.setText("WATCH OUT! Score: " + (snake.getLength()));
            } else if (snake.hitItself()) {
                playing = false;
                status.setText("YOU ATE YOURSELF! :0 Score: " + snake.getLength());
            } else {
                if (snake.getLength() > 3 && snake.getLength() < 10) {
                    setInterval(80);
                } else if (snake.getLength() > 10) {
                    setInterval(50);
                }
                snake.move();
            }
            repaint();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        apple.draw(g);
        poison.draw(g);
    }

}