package de.bundeswehr.auf.slaythespire.model.settings.structure;

public class Easy extends DifficultyLevel {

    public Easy() {
        super(50, 0, 25, 50, 1.5, 5, 0.8);
    }

    @Override
    public int modifyDamage(int damage) {
        return Math.max(1, damage / 2);
    }

}
