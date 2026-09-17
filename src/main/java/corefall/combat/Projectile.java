package corefall.combat;

import corefall.entity.Player;
import java.awt.Color;
import java.awt.Graphics2D;

public class Projectile {

    private double x;
    private double y;

    private double dx;
    private double dy;

    private final int size = 8;
    private final double speed = 3;
    private int lifetime = 240;

    public Projectile(double x, double y, double dx, double dy) {

        this.x = x;
        this.y = y;

        double length = Math.sqrt(dx * dx + dy * dy);

        this.dx = dx / length;
        this.dy = dy / length;
    }

    public void update(Player player) {

        double targetDx = player.getX() - x;
        double targetDy = player.getY() - y;

        double targetLength = Math.sqrt(targetDx * targetDx + targetDy * targetDy);

        if (targetLength > 0) {
            targetDx /= targetLength;
            targetDy /= targetLength;

            dx = dx * 0.92 + targetDx * 0.08;
            dy = dy * 0.92 + targetDy * 0.08;

            double directionLength = Math.sqrt(dx * dx + dy * dy);

            dx /= directionLength;
            dy /= directionLength;
        }

        x += dx * speed;
        y += dy * speed;
        lifetime--;
    }

    public void draw(Graphics2D g2) {

        g2.setColor(new Color(255, 120, 120));
        g2.fillOval((int) x, (int) y, size, size);
    }

    public boolean isOutsideScreen() {

        return x < -size || y < -size
                || x > 1280 + size
                || y > 720 + size;
    }

    public boolean isExpired() {
        return lifetime <= 0;
    }
}