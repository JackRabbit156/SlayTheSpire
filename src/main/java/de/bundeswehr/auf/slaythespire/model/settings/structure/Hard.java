package de.bundeswehr.auf.slaythespire.model.settings.structure;

public class Hard extends DifficultyLevel {

    public Hard() {
        super(90, 50, 75, 100, 0.5, 1, 0.1);
    }

    @Override
    public int getHealth(int hp) {
        return hp * 2;
    }

}
