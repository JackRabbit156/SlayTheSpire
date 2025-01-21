package de.bundeswehr.auf.slaythespire.model.relic.structure;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;

import java.util.Random;

/**
 * In dieser Klasse werden Relics definiert.
 *
 * @author OF Daniel Willig
 */
public abstract class Relic {

    private static final Random rnd = new Random();

    private final String description;
    private String imagePath;
    private final String name;
    private final int price;
    private final RelicRarity rarity;
    private final RelicTrigger trigger;

    protected Relic(String name, String description, RelicRarity rarity, RelicTrigger trigger) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.trigger = trigger;
        price = generatePrice();
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

    public int getPrice() {
        return price;
    }

    private int generatePrice() {
        // https://slay-the-spire.fandom.com/wiki/Merchant
        switch (rarity) {
            case UNCOMMON:
                return rnd.nextInt(262 + 1 - 238) + 238;
            case RARE:
                return rnd.nextInt(315 + 1 - 285) + 285;
            case COMMON:
            default:
                return rnd.nextInt(157 + 1 - 143) + 143;
        }
    }

}
