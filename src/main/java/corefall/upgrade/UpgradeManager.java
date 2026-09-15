package corefall.upgrade;
import java.util.ArrayList;
import java.util.List;

public class UpgradeManager {

    private final List<Upgrade> upgradePool = new ArrayList<>();

    public UpgradeManager() {
        loadUpgrades();
    }

    private void loadUpgrades() {
        upgradePool.add(new Upgrade("Dano Base", "+2 dano"));
        upgradePool.add(new Upgrade("Rapidez", "+3 velocidade"));
        upgradePool.add(new Upgrade("Vitalidade", "+5 vida máxima"));
        upgradePool.add(new Upgrade("Cadência", "+5 velocidade de ataque"));
        upgradePool.add(new Upgrade("Alcance", "+3 alcance"));
    }

    public List<Upgrade> getUpgradePool() {
        return upgradePool;
    }
}