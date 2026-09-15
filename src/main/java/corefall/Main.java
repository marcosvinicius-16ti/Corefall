package corefall;

import javax.swing.JFrame;

import corefall.engine.GamePanel;
import corefall.util.Constants;

public class Main {

    public static void main(String[] args) {
        JFrame window = new
JFrame(Constants.GAME_TITLE);

        GamePanel gamePanel = new GamePanel();

window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setResizable(false);
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}