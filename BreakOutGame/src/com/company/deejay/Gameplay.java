package com.company.deejay;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;

    private Timer timer;
    private int delay = 4;

    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private BrickGrid grid;

    public Gameplay() {
        grid = new BrickGrid(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g) {
        // background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        //drawing grid
        grid.draw((Graphics2D)g);

        //border
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        //paddle
        g.setColor(Color.cyan);
        g.fillRect(playerX, 550, 100, 8);

        //score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("YOUR SCORE:  " + score, 390, 30);

        //ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);

        //win condition
        if (score >= 105) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 35));
            g.drawString("YOU WON", 260, 300);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("PRESS ENTER TO PLAY AGAIN", 200, 350);
        }

        //loss condition
        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 45));
            g.drawString("GAME OVER", 190, 270);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("YOUR FINAL SCORE:  " + score, 155, 320);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("PRESS ENTER TO RESTART", 195, 355);

        }
        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = - ballYdir;
            }
            A: for (int i = 0; i < grid.grid.length; i++) {
                for (int j = 0; j < grid.grid[0].length; j++) {
                    if (grid.grid[i][j] > 0) {
                        int brickX = j* grid.brickwidth+80;
                        int brickY = i* grid.brickheight+50;
                        int brickwidth = grid.brickwidth;
                        int brickheight = grid.brickheight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickwidth, brickheight);
                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickrect = rect;

                        if (ballrect.intersects(brickrect)) {
                            grid.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            if (ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + brickrect.width) {
                                ballXdir = - ballXdir;
                            }
                            else {
                                ballYdir = - ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) {
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            }
            else {
                moveRight();
            }
    }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX <= 8) {
                playerX = 8;
            }
            else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!play || score >= 105) {
                play = true;
                ballposX = 0;
                ballposY = 0;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                grid = new BrickGrid(3, 7);

                repaint();
            }
        }

    }
    public void moveRight() {
        play = true;
        playerX += 20;
    }
    public void moveLeft() {
        play = true;
        playerX -= 20;
    }
}
