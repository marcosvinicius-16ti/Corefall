package corefall.entity;

public class PlayerStats {

    private int maxHealth;
    private int currentHealth;
    private int baseDamage;
    private int speed;
    private int attackSpeed;
    private int range;
    private int level;
    private int currentXp;
    private int xpToNextLevel;
    private int invincibilityFrames;

    public PlayerStats() {
        this.maxHealth = 20;
        this.currentHealth = 20;
        this.baseDamage = 2;
        this.speed = 1;
        this.attackSpeed = 10;
        this.range = 10;
        this.level = 1;
        this.currentXp = 0;
        this.xpToNextLevel = 10;
        this.invincibilityFrames = 0;
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

    public int getLevel() {
        return level;
    }

    public int getCurrentXp() {
        return currentXp;
    }

    public int getXpToNextLevel() {
        return xpToNextLevel;
    }

    public int getInvincibilityFrames() {
        return invincibilityFrames;
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

    public boolean addXp(int value) {
        currentXp += value;
        if (currentXp >= xpToNextLevel) {
            currentXp -= xpToNextLevel;
            level++;
            xpToNextLevel += 10;
            return true;
        }
        return false;
    }

    public void takeDamage(int damage) {

        if (invincibilityFrames > 0) {
            return;
        }

        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
        invincibilityFrames = 30;
    }

    public void update() {
        if (invincibilityFrames > 0) {
            invincibilityFrames--;
        }
    }
}