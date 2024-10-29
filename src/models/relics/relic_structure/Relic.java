package models.relics.relic_structure;

import models.GameContext;

public abstract class Relic {
    // * Variables *
    private String name;
    private String description;

    private RelicType rarity;


    // * Constructor *
    protected Relic(String name, String description, RelicType rarity) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
    }

    public abstract void getsUsed(GameContext gameContext);

    // * Getter & Setter *
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    // * toString *
}
