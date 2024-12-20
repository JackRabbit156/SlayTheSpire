package de.bundeswehr.auf.slaythespire.model.relic.structure;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;

/**
 * In dieser Klasse werden Relics definiert.
 *
 * @author OF Daniel Willig
 */
public abstract class Relic {

    private final String description;
    private String imagePath;
    private final String name;
    private final RelicRarity rarity;
    private final RelicTrigger trigger;

    protected Relic(String name, String description, RelicRarity rarity, RelicTrigger trigger) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.trigger = trigger;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public RelicRarity getRarity() {
        return rarity;
    }

    public RelicTrigger getTrigger() {
        return trigger;
    }

    /**
     * was soll passieren, wenn das Relics "sich aktiviert"
     *
     * @param gameContext the game context
     */
    public abstract void activate(GameContext gameContext);

}
