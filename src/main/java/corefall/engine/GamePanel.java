
package corefall.engine;

import java.awt.Graphics2D;
import corefall.entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import corefall.upgrade.Upgrade;


import javax.swing.JPanel;

import corefall.util.Constants;

public class GamePanel extends JPanel implements Runnable {

    private final Player player = new Player(
            Constants.SCREEN_WIDTH / 2.0 - 15,
            Constants.SCREEN_HEIGHT / 2.0 - 15);
    private Thread gameThread;
    private GameState gameState = GameState.MENU;
    private final Rectangle playButton = new Rectangle(530,420, 220,46);

    public GamePanel() {

        setPreferredSize(new Dimension(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT));

        setBackground(new Color(17, 17, 17));
        setDoubleBuffered(true);

        addMouseListener(new MouseAdapter() {
            @Override 
            public void mouseClicked(MouseEvent e) {
                if (gameState == GameState.MENU &&
                    playButton.contains(e.getPoint())) {

                    gameState = GameState.START_SELECTION;
                    repaint();
                    }
            }
        });
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

        switch (gameState) {
            case MENU -> drawMenu(g2);
            case PLAYING -> player.draw(g2);
            default -> {}
        }

        g2.dispose();

    }

    private void drawMenu(Graphics2D g2) {

    g2.setColor(Color.WHITE);

    // Título
    g2.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 52));

    String title = "COREFALL";
    var metrics = g2.getFontMetrics();

    int x = (Constants.SCREEN_WIDTH - metrics.stringWidth(title)) / 2;
    int y = 220;

    g2.drawString(title, x, y);

    // Linhas laterais
    g2.drawLine(x - 90, y - 18, x - 20, y - 18);
    g2.drawLine(x + metrics.stringWidth(title) + 20, y - 18,
            x + metrics.stringWidth(title) + 90, y - 18);

    // Botão Play
    g2.drawRect(
            playButton.x,
            playButton.y,
            playButton.width,
            playButton.height
    );

    

    g2.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 20));

    String play = "PLAY";
    var playMetrics = g2.getFontMetrics();

    g2.drawString(
            play,
            playButton.x +
                    (playButton.width - playMetrics.stringWidth(play)) / 2,
            playButton.y + 29
    );
    }
}