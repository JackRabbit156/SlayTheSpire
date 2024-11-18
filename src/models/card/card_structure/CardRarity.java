package models.card.card_structure;

/**
 * Enum Card Seltenheit.
 * Typen der Seltenheit einer Karte
 *
 * @author OF Daniel Willig
 * @author F Alex Warawa
 */
public enum CardRarity {
    /**
     * gewöhnliche Karte.
     */
    COMMON,
    /**
     * ungewöhnliche Karte.
     */
    UNCOMMON,
    /**
     * seltene Karte.
     */
    RARE,
    /**
     * die Startkarten
     */
    BASIC, //TODO (https://slay-the-spire.fandom.com/wiki/Cards)
    /**
     * Spezielle Karte
     */
    SPECIAL,
    /**
     * Potions
     */
    POTION
}
