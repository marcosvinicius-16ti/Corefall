package corefall.combat;

import corefall.entity.Player;
import corefall.entity.Enemy;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.util.List;

public class Aura {

    private final Player player;
    private int attackCooldown;

    public Aura(Player player) {
        this.player = player;
        this.attackCooldown = 0;
    }

    public void draw(Graphics2D g2) {

        int radius = player.getAttackRadius();

        int centerX = (int) player.getX() + player.getSize() / 2;
        int centerY = (int) player.getY() + player.getSize() / 2;

        Composite original = g2.getComposite();

        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER,
                0.10f));

        g2.setColor(Color.WHITE);

        g2.fillOval(
                centerX - radius,
                centerY - radius,
                radius * 2,
                radius * 2);

        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER,
                0.30f));

        g2.drawOval(
                centerX - radius,
                centerY - radius,
                radius * 2,
                radius * 2);

        g2.setComposite(original);
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public void update(List<Enemy> enemies) {

        if (attackCooldown > 0) {
            attackCooldown--;
            return;
        }

        double centerX = player.getX() + player.getSize() / 2.0;
        double centerY = player.getY() + player.getSize() / 2.0;

        boolean attacked = false;

        for (Enemy enemy : enemies) {

            double enemyCenterX = enemy.getX() + enemy.getSize() / 2.0;
            double enemyCenterY = enemy.getY() + enemy.getSize() / 2.0;

            double dx = enemyCenterX - centerX;
            double dy = enemyCenterY - centerY;

            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance <= player.getAttackRadius()) {
                enemy.takeDamage(player.getStats().getBaseDamage());
                attacked = true;
            }
        }

        if (attacked) {
            attackCooldown = Math.max(
                    8,
                    70 - player.getStats().getAttackSpeed() * 4);
        }
    }
}