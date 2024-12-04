package models.relic.relic_structure;

import models.battle.GameContext;

/**
 * In dieser Klasse werden Relics definiert.
 *
 * @author OF Daniel Willig
 */
public abstract class Relic {

    /**
     * Die Deskription.
     */
    private final String description;
    private String imagePath;
    /**
     * Der Name.
     */
    private final String name;
    /**
     * Die Seltenheit.
     */
    private final RelicType rarity;
    private final RelicTrigger relicTrigger;


    /**
     * Constructor Relic.
     *
     * @param name        der Name
     * @param description die Deskription
     * @param rarity      Die Seltenheit
     */
    protected Relic(String name, String description, RelicType rarity, RelicTrigger relicTrigger) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.relicTrigger = relicTrigger;
    }

    /**
     * getter description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

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

    public RelicTrigger getRelicTrigger() {
        return relicTrigger;
    }

    /**
     * was soll passieren, wenn das Relics "sich aktiviert"
     *
     * @param gameContext the game context
     */
    public abstract void getsUsed(GameContext gameContext);

}
