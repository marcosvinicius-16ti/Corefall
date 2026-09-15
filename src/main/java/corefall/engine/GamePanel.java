
package corefall.engine;

import java.awt.Graphics2D;
import corefall.entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import corefall.util.Constants;

public class GamePanel extends JPanel implements Runnable {

    private final Player player = new Player(
            Constants.SCREEN_WIDTH / 2.0 - 15,
            Constants.SCREEN_HEIGHT / 2.0 - 15);
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

        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);

        g2.dispose();

    }
}