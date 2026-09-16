package corefall.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import corefall.entity.PlayerStats;

public class HUD {

    public void draw(Graphics2D g2, PlayerStats stats) {

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));

        g2.drawString("Vida: " + stats.getCurrentHealth() + "/" + stats.getMaxHealth(), 20, 30);
        g2.drawString("Dano: " + stats.getBaseDamage(), 20, 55);
        g2.drawString("Cadência: " + stats.getAttackSpeed(), 20, 80);
        g2.drawString("Velocidade: " + stats.getSpeed(), 20, 105);
        g2.drawString("Alcance: " + stats.getRange(), 20, 130);

        g2.drawString("Nível: " + stats.getLevel(), 20, 170);
        g2.drawString("XP: " + stats.getCurrentXp() + "/" + 
        stats.getXpToNextLevel(), 20, 195);
    }
}