package corefall.entity;
import corefall.combat.Projectile;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy {

    private double x;
    private double y;

    private final int size = 24;

    private final EnemyType type;
    private int attackCooldown = 360;
    private final double speed = 0.8;

    public Enemy(double x, double y, EnemyType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void update(Player player) {

        if (type != EnemyType.CHASER) {
            return;
        }
        double dx = player.getX() - x;
        double dy = player.getY() - y;

        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > 0) {
            x += (dx / distance) * speed;
            y += (dy / distance) * speed;
        }
    }

    public void draw(Graphics2D g2) {

        g2.setColor(new Color(220, 70, 70));

        switch (type) {

            case CHASER ->
                g2.fillOval((int) x, (int) y, size, size);

            case SHOOTER ->
                g2.fillRect((int) x, (int) y, size, size);
        }
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

    public EnemyType getType() {
        return type;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public Projectile tryShoot(Player player) {

        if (type != EnemyType.SHOOTER) {
            return null;
        }

        attackCooldown--;

        if (attackCooldown > 0) {
            return null;
        }

        attackCooldown = 360;

        double centerX = x + size / 2.0;
        double centerY = y + size / 2.0;

        double dx = player.getX() + player.getSize() / 2.0 - centerX;
        double dy = player.getY() + player.getSize() / 2.0 - centerY;

        return new Projectile(centerX, centerY, dx, dy);
    }
}