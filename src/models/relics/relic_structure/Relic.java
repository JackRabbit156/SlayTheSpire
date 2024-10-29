package models.relics.relic_structure;

import models.GameContext;

public abstract class Relic {
    // * Variables *
    private final String name;
    private final String description;

    private final RelicType rarity;


    // * Constructor *
    protected Relic(String name, String description, RelicType rarity) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
    }

    // * Methods *
    public abstract void getsUsed(GameContext gameContext);


    // * Getter & Setter *
    public String getName() {
        return name;
    }

    public RelicType getRarity() {
        return rarity;
    }

    public String getDescription() {
        return description;
    }

    // * toString *
}
