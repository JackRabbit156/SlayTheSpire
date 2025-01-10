package de.bundeswehr.auf.slaythespire.model.settings.structure;

public class Impossible extends DifficultyLevel {

    public Impossible() {
        super(100, 0.1, 1, 0.0);
    }

    @Override
    public int modifyDamage(int damage) {
        return damage * 2;
    }

    public int modifyGold(int gold) {
        return 1;
    }

    @Override
    public int modifyHealth(int hp) {
        return hp * 2;
    }

    public int getNumberOfEnemies() {
        return 5;
    }

}
