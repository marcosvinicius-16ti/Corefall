
package corefall.engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import corefall.util.Constants;

public class GamePanel extends JPanel implements Runnable {

    private Thread gameThread;

    public GamePanel() {

        setPreferredSize(new Dimension(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT));

        setBackground(new Color(17, 17, 17));
        setDoubleBuffered(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while (gameThread != null) {

            repaint();

            try {
                Thread.sleep(1000 / Constants.FPS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.WHITE);

        int size = 30;

        g.fillOval(
                Constants.SCREEN_WIDTH / 2 - size / 2,
                Constants.SCREEN_HEIGHT / 2 - size / 2,
                size,
                size);
    }
}