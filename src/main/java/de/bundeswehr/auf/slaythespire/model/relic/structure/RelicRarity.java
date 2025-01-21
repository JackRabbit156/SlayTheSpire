package de.bundeswehr.auf.slaythespire.model.relic.structure;

/**
 * Die Seltenheit von Relics.
 *
 * @author L Frank Rieger
 */
public enum RelicRarity {

    /**
     * Starter Relics are granted upon starting a run with the corresponding character.
     */
    STARTER,

    /**
     * Gewöhnlich.
     */
    COMMON,
    /**
     * Ungewöhnlich.
     */
    UNCOMMON,
    /**
     * Selten.
     */
    RARE,

    /**
     * Boss Relics can only be found from Bosses.
     */
    BOSS,
    /**
     * Event Relics can only be found from specific event outcomes.
     */
    EVENT,
    /**
     * Shop Relics can only be purchased from the Merchant.
     */
    SHOP,

    SPECIAL

}
