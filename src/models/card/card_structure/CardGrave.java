package models.card.card_structure;

/**
 * Enum Card "Grab".
 * Wo geht die Karte hin nachdem sie gespielt wurde
 *
 * @author OF Daniel Willig
 */
public enum CardGrave {
    /**
     * Karte geht in den Exhaust Pile.
     */
    EXHAUST,
    /**
     * Karte geht in den Discard Pile.
     */
    DISCARD,
    /**
     * Karte wird einfach nur gelöscht.
     */
    NONE,
    /**
     * Extra für Potions.
     */
    POTION
}
