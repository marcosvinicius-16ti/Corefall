
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
import corefall.upgrade.UpgradeManager;
import java.awt.Font;

import javax.swing.JPanel;

import corefall.util.Constants;

public class GamePanel extends JPanel implements Runnable {
    private final KeyHandler keyHandler = new KeyHandler();
    private final Player player = new Player(
            Constants.SCREEN_WIDTH / 2.0 - 15,
            Constants.SCREEN_HEIGHT / 2.0 - 15,
            keyHandler);
    private Thread gameThread;
    private GameState gameState = GameState.MENU;
    private final Rectangle playButton = new Rectangle(530, 420, 220, 46);
    private final UpgradeManager upgradeManager = new UpgradeManager();
    private Upgrade[] upgradeChoices = new Upgrade[3];
    private Upgrade[] currentChoices = new Upgrade[3];
    private final Rectangle[] upgradeCards = {
        new Rectangle(220,220,220,260),
        new Rectangle(530,220,220,260),
        new Rectangle(840,220,220,260)
    };
    

    public GamePanel() {

        setPreferredSize(new Dimension(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT));

        setBackground(new Color(17, 17, 17));
        setDoubleBuffered(true);

        addKeyListener(keyHandler);
        setFocusable(true);
        requestFocusInWindow();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameState == GameState.MENU &&
                        playButton.contains(e.getPoint())) {

                    currentChoices = upgradeManager.generateUpgradeChoices();

                    gameState = GameState.START_SELECTION;
                    repaint();
                }
                else if (gameState == GameState.START_SELECTION) {
                    for (int i = 0; i < upgradeCards.length; i++) {
                        if (upgradeCards[i].contains(e.getPoint())) {
                            currentChoices[i].apply(player.getStats());

                            gameState = GameState.PLAYING;
                            repaint();
                            
                            break;
                        }
                    }
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

            if (gameState == GameState.PLAYING) {
                player.update();
            }

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
            case START_SELECTION -> drawStartSelection(g2);
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
                playButton.height);

        g2.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 20));

        String play = "PLAY";
        var playMetrics = g2.getFontMetrics();

        g2.drawString(
                play,
                playButton.x +
                        (playButton.width - playMetrics.stringWidth(play)) / 2,
                playButton.y + 29);
    }

    private void drawStartSelection(Graphics2D g2) {

        if (currentChoices[0] == null) {
            return;
        }

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("SansSerif", Font.BOLD, 32));

        String title = "Escolha sua primeira habilidade";
        var metrics = g2.getFontMetrics();

        g2.drawString(
                title,
                (Constants.SCREEN_WIDTH - metrics.stringWidth(title)) / 2,
                120);

        for (int i = 0; i < upgradeCards.length; i++) {

            Rectangle card = upgradeCards[i];
            Upgrade upgrade = currentChoices[i];

            g2.drawRect(card.x, card.y, card.width, card.height);

            g2.setFont(new Font("SansSerif", Font.BOLD, 22));
            var titleMetrics = g2.getFontMetrics();

            g2.drawString(
                    upgrade.getDisplayName(),
                    card.x + (card.width -
                            titleMetrics.stringWidth(upgrade.getDisplayName())) / 2,
                    card.y + 70);

            g2.setFont(new Font("SansSerif", Font.PLAIN, 16));
            var descMetrics = g2.getFontMetrics();

            g2.drawString(
                    upgrade.getDescription(),
                    card.x + (card.width -
                            descMetrics.stringWidth(upgrade.getDescription())) / 2,
                    card.y + 130);
        }
    }

}