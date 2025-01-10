package de.bundeswehr.auf.slaythespire.model.settings.structure;

public class SuperEasy extends DifficultyLevel {

    public SuperEasy() {
        super(25, 1.5, 7, 1.0);
    }

    @Override
    public int modifyDamage(int damage) {
        return Math.max(1, damage / 2);
    }

    @Override
    public int modifyHealth(int hp) {
        return hp / 2;
    }

    public int getNumberOfEnemies() {
        return 1;
    }

}
