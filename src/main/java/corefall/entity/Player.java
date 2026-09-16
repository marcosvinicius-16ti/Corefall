package corefall.entity;
import corefall.engine.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;

public class Player {

    private double x;
    private double y;

    private final int size = 30;

    private final PlayerStats stats;

    private final KeyHandler keyHandler;

    public Player(double x, double y, KeyHandler keyHandler) {
        this.x = x;
        this.y = y;
        this.stats = new PlayerStats();
        this.keyHandler = keyHandler;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillOval((int) x, (int) y, size, size);
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

    public void update() {

        int speed = stats.getSpeed();

        if (keyHandler.upPressed) {
            y -= speed;
        }

        if (keyHandler.downPressed) {
            y += speed;
        }

        if (keyHandler.leftPressed) {
            x -= speed;
        }

        if (keyHandler.rightPressed) {
            x += speed;
        }
    }
}