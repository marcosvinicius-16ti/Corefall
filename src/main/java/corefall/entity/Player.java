package corefall.entity;

import corefall.util.Constants;
import corefall.engine.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Composite;
import corefall.combat.Aura;

public class Player {

    private double x;
    private double y;

    private final Aura aura;

    private final int size = 30;

    private final PlayerStats stats;

    private final KeyHandler keyHandler;

    public int getAttackRadius() {
        return stats.getRange() * 8;
    }

    public Player(double x, double y, KeyHandler keyHandler) {
        this.x = x;
        this.y = y;
        this.stats = new PlayerStats();
        this.keyHandler = keyHandler;
        this.aura = new Aura(this);
    }

    public void draw(Graphics2D g2) {

        aura.draw(g2);
        drawHealthBar(g2);

        if (stats.getInvincibilityFrames() % 4 < 2) {
            g2.setColor(new Color(255, 120, 120));
        } else {
            g2.setColor(Color.WHITE);
        }
        g2.fillOval((int) x, (int) y, size, size);
    }

    private void drawHealthBar(Graphics2D g2) {

        int barWidth = size;
        int barHeight = 5;

        int barX = (int) x;
        int barY = (int) y - 10;

        double healthPercent = stats.getCurrentHealth() / (double) stats.getMaxHealth();

        // Fundo da barra
        g2.setColor(new Color(50, 50, 50));
        g2.fillRoundRect(barX, barY, barWidth, barHeight, 5, 5);

        // Cor dinâmica
        if (healthPercent > 0.7) {
            g2.setColor(new Color(80, 255, 80));
        } else if (healthPercent > 0.3) {
            g2.setColor(new Color(255, 220, 70));
        } else {
            g2.setColor(new Color(255, 80, 80));
        }

        int currentWidth = (int) (barWidth * healthPercent);

        g2.fillRoundRect(barX, barY, currentWidth, barHeight, 5, 5);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public Aura getAura() {
        return aura;
    }

    public void update() {

        stats.update();

        double dx = 0;
        double dy = 0;

        int speed = stats.getSpeed();

        if (keyHandler.upPressed)
            dy--;
        if (keyHandler.downPressed)
            dy++;
        if (keyHandler.leftPressed)
            dx--;
        if (keyHandler.rightPressed)
            dx++;

        if (dx != 0 || dy != 0) {

            double length = Math.sqrt(dx * dx + dy * dy);

            dx = dx / length * speed;
            dy = dy / length * speed;

            x += dx;
            y += dy;
        }

        x = Math.max(0, Math.min(x, Constants.SCREEN_WIDTH - size));
        y = Math.max(0, Math.min(y, Constants.SCREEN_HEIGHT - size));
    }
}