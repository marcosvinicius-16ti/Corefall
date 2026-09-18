package corefall.entity;

import corefall.combat.Projectile;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy {

    private double x;
    private double y;

    private final int size = 24;

    private final EnemyType type;
    private final EnemyStats stats;
    private int hitFlashFrames = 0;
    public boolean dying = false;
    private int deathAnimationFrames = 6;

    public Enemy(double x, double y, EnemyType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.stats = new EnemyStats(type);
    }

    public void update(Player player) {

        if (dying) {
            return;
        }

        if (type != EnemyType.CHASER) {
            return;
        }

        double dx = player.getX() - x;
        double dy = player.getY() - y;

        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > 0) {
            x += (dx / distance) * stats.getSpeed();
            y += (dy / distance) * stats.getSpeed();
        }
    }

    public void draw(Graphics2D g2) {

        if (hitFlashFrames > 0) {
            hitFlashFrames--;
        }

        if (dying && deathAnimationFrames > 0) {
            deathAnimationFrames--;
        }

        if (hitFlashFrames > 0) {
            g2.setColor(Color.WHITE);
        } else {
            g2.setColor(new Color(220, 70, 70));
        }

        int drawSize = size;

        if (dying) {
            drawSize = Math.max(12, size - (6 - deathAnimationFrames)
                    * 2);
        }

        switch (type) {

            case CHASER ->
                g2.fillOval((int) x, (int) y, drawSize, drawSize);

            case SHOOTER -> {

                if (stats.getAttackCooldown() <= 15 &&
                        hitFlashFrames == 0) {
                    g2.setColor(Color.WHITE);
                } else if (hitFlashFrames == 0) {
                    g2.setColor(new Color(220, 70, 70));
                }

                g2.fillRect((int) x, (int) y, drawSize, drawSize);
            }
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
        return stats.getAttackCooldown();
    }

    public void setAttackCooldown(int attackCooldown) {
        stats.setAttackCooldown(attackCooldown);
    }

    public Projectile tryShoot(Player player) {

        if (dying) {
            return null;
        }

        if (type != EnemyType.SHOOTER) {
            return null;
        }

        stats.setAttackCooldown(stats.getAttackCooldown() - 1);

        if (stats.getAttackCooldown() > 0) {
            return null;
        }

        stats.setAttackCooldown(360);

        double centerX = x + size / 2.0;
        double centerY = y + size / 2.0;

        double dx = player.getX() + player.getSize() / 2.0 - centerX;
        double dy = player.getY() + player.getSize() / 2.0 - centerY;

        return new Projectile(centerX, centerY, dx, dy);
    }

    public boolean collidesWith(Player player) {

        if (type != EnemyType.CHASER) {
            return false;
        }

        double enemyCenterX = x + size / 2.0;
        double enemyCenterY = y + size / 2.0;

        double playerCenterX = player.getX() + player.getSize() / 2.0;
        double playerCenterY = player.getY() + player.getSize() / 2.0;

        double dx = enemyCenterX - playerCenterX;
        double dy = enemyCenterY - playerCenterY;

        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance < (size + player.getSize()) / 2.0;
    }

    public void takeDamage(int damage) {
        stats.takeDamage(damage);
        hitFlashFrames = 8;

        if (stats.isDead()) {
            dying = true;
        }
    }

    public boolean isDead() {
        return stats.isDead();
    }

    public EnemyStats getStats() {
        return stats;
    }

    public boolean shouldRemove() {
        return dying && deathAnimationFrames <= 0;
    }

}