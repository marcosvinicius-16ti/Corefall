package corefall.entity;

import java.util.ArrayList;
import java.util.List;

public class SpawnManager {

    private final List<Enemy> enemies = new ArrayList<>();

    public SpawnManager() {

        enemies.add(new Enemy(150, 120, EnemyType.CHASER));
        enemies.add(new Enemy(900, 180, EnemyType.SHOOTER));
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void update() {
        enemies.removeIf(Enemy::shouldRemove);
    }
}