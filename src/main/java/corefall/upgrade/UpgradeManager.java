package corefall.upgrade;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class UpgradeManager {

    private final List<Upgrade> upgradePool = new ArrayList<>();

    public UpgradeManager() {
        loadUpgrades();
    }

    private void loadUpgrades() {

    upgradePool.add(new Upgrade("Dano Base", UpgradeType.BASE_DAMAGE));
    upgradePool.add(new Upgrade("Agilidade", UpgradeType.SPEED));
    upgradePool.add(new Upgrade("Vida Extra", UpgradeType.HEALTH));
    upgradePool.add(new Upgrade("Velocidade de Ataque", UpgradeType.ATTACK_SPEED));
    upgradePool.add(new Upgrade("Alcance", UpgradeType.RANGE));
}

    public List<Upgrade> getUpgradePool() {
        return upgradePool;
    }

    public Upgrade[] generateUpgradeChoices() {

        List<Upgrade> available = new ArrayList<>(upgradePool);
        Collections.shuffle(available);

        Upgrade[] choices = new Upgrade[3];
        for (int i = 0; i < 3; i++) {
            Upgrade original = available.get(i);

            choices[i] = new Upgrade(
                original.getName(), original.getType());
        }
        return choices;
    }
}