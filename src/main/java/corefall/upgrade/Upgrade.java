package corefall.upgrade;

public class Upgrade {

    private final String name;
    private final UpgradeType type;
    private int level;

    public Upgrade(String name, UpgradeType type) {
        this.name = name;
        this.type = type;
        this.level = 1;
    }

    public void levelUp() {
        level++;
    }

    public String getName() {
        return name;
    }

    public UpgradeType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public String getDisplayName() {
        return name + " " + toRoman(level);
    }

    public String getDescription() {
        return "+" + getUpgradeValue() + " " + getAttributeName();
    }

    public int getUpgradeValue() {

        return switch (type) {
            case BASE_DAMAGE -> switch (level) {
                case 1 -> 1;
                case 2 -> 3;
                default -> 5;
            };

            case SPEED -> switch (level) {
                case 1 -> 1;
                case 2 -> 2;
                default -> 3;
            };

            case HEALTH -> switch (level) {
                case 1 -> 5;
                case 2 -> 8;
                default -> 12;
            };

            case ATTACK_SPEED -> switch (level) {
                case 1 -> 2;
                case 2 -> 4;
                default -> 6;
            };

            case RANGE -> switch (level) {
                case 1 -> 2;
                case 2 -> 4;
                default -> 6;
            };
        };
    }

    public void apply(corefall.entity.PlayerStats stats) {

        switch (type) {
            case BASE_DAMAGE ->
    stats.addBaseDamage(getUpgradeValue());
            case SPEED -> 
    stats.addSpeed(getUpgradeValue());
            case HEALTH -> 
    stats.addMaxHealth(getUpgradeValue());
            case ATTACK_SPEED -> 
    stats.addAttackSpeed(getUpgradeValue());
            case RANGE -> 
    stats.addRange(getUpgradeValue());
        }
    }

    private String getAttributeName() {

        return switch (type) {
            case BASE_DAMAGE -> "Dano Base";
            case SPEED -> "Agilidade";
            case HEALTH -> "Vida Extra";
            case ATTACK_SPEED -> "Velocidade de Ataque";
            case RANGE -> "Alcance";
        };
    }

    private String toRoman(int value) {

        return switch (value) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            default -> String.valueOf(value);
        };
    }
}