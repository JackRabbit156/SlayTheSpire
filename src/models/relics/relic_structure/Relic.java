package models.relics.relic_structure;

import models.GameContext;

/**
 * In dieser Klasse werden Relics definiert.
 *
 * @author OF Daniel Willig
 */
public abstract class Relic {
// * Variables *
    /**
     * Der Name.
     */
    private final String name;
    /**
     * Die Deskription.
     */
    private final String description;

    /**
     * Die Seltenheit.
     */
    private final RelicType rarity;


    /**
     * Constructor Relic.
     *
     * @param name        der Name
     * @param description die Deskription
     * @param rarity      Die Seltenheit
     */
// * Constructor *
    protected Relic(String name, String description, RelicType rarity) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
    }

    /**
     * was soll passieren, wenn das Relics "sich aktiviert"
     *
     * @param gameContext the game context
     */
// * Methods *
    public abstract void getsUsed(GameContext gameContext);


// * Getter & Setter *
    /**
     * getter name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * getter rarity.
     *
     * @return the rarity
     */
    public RelicType getRarity() {
        return rarity;
    }

    /**
     * getter description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    // * toString *
}
