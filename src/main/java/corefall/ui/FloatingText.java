package corefall.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class FloatingText {

    private String text;
    private double x;
    private double y;
    private int life;

    public FloatingText(String text, double x, double y) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.life = 48; // ~0,8s em 60 FPS
    }

    public void update() {
        y -= 0.5;
        life--;
    }

    public void draw(Graphics2D g2) {

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("SansSerif", Font.BOLD, 18));

        g2.drawString(text, (int)x, (int)y);
    }

    public boolean isAlive() {
        return life > 0;
    }
}