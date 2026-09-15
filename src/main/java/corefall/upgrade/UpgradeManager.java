package corefall.upgrade;
import java.util.ArrayList;
import java.util.List;

public class UpgradeManager {

    private final List<Upgrade> upgradePool = new ArrayList<>();

    public UpgradeManager() {
        loadUpgrades();
    }

    private void loadUpgrades() {

    upgradePool.add(new Upgrade("Dano Base", UpgradeType.BASE_DAMAGE));
    upgradePool.add(new Upgrade("Rapidez", UpgradeType.SPEED));
    upgradePool.add(new Upgrade("Vitalidade", UpgradeType.HEALTH));
    upgradePool.add(new Upgrade("Cadência", UpgradeType.ATTACK_SPEED));
    upgradePool.add(new Upgrade("Alcance", UpgradeType.RANGE));
}

    public List<Upgrade> getUpgradePool() {
        return upgradePool;
    }
}