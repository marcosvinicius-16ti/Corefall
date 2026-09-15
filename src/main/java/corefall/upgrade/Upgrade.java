package corefall.upgrade;

public class Upgrade {

    private final String name;
    private final String description;
    private int level;

    public Upgrade(String name, String description) {
        this.name = name;
        this.description = description;
        this.level = 1;
    }
    public void levelUp() {
        level++;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplayName() {
        return name + " " + level;
    }
}