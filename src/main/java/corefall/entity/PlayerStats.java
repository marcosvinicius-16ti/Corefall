package corefall.entity;

public class PlayerStats {

    private int maxHealth;
    private int currentHealth;
    private int baseDamage;
    private int speed;
    private int attackSpeed;
    private int range;

    public PlayerStats() {
        this.maxHealth = 20;
        this.currentHealth = 20;
        this.baseDamage = 2;
        this.speed = 1;
        this.attackSpeed = 10;
        this.range = 10;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int getRange() {
        return range;
    }

    public void addMaxHealth(int value) {
        maxHealth += value;
        currentHealth += value;
    }

    public void addBaseDamage(int value) {
        baseDamage += value;
    }

    public void addSpeed(int value) {
        speed += value;
    }

    public void addAttackSpeed(int value) {
        attackSpeed += value;
    }

    public void addRange(int value) {
        range += value;
    }
}