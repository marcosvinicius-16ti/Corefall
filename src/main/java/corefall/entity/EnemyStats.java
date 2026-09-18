package corefall.entity;

public class EnemyStats {

    private int maxHealth;
    private int currentHealth;
    private int damage;
    private double speed;
    private int attackCooldown;
    private int xpDrop;

    public EnemyStats(EnemyType type) {

        switch (type) {

            case CHASER -> {
                maxHealth = 5;
                currentHealth = 5;
                damage = 1;
                speed = 0.8;
                attackCooldown = 0;
                xpDrop = 2;
            }

            case SHOOTER -> {
                maxHealth = 8;
                currentHealth = 8;
                damage = 3;
                speed = 0;
                attackCooldown = 360;
                xpDrop = 5;
            }
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(int value) {
        attackCooldown = value;
    }

    public int getXpDrop() {
        return xpDrop;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }
}