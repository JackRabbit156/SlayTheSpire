package models.cards.card_structure;

import models.GameContext;

/**
 * Die Power card.
 *
 * @author OF Daniel Willig
 */
public abstract class PowerCard extends Card {
    /**
     * Wann die Power getriggert wird.
     */
    CardTrigger cardTrigger;

    /**
     * Constructor PowerCard.
     *
     * @param name        der Name
     * @param description die Beschreibung
     * @param cost        die Energiekosten
     * @param rarity      die Seltenheit
     * @param cardGrave   wo die Karte hingeht nachdem die gespielt worden ist
     * @param cardTrigger was der Karten-Trigger ist
     */
    public PowerCard(String name, String description, int cost, CardRarity rarity, CardGrave cardGrave, CardTrigger cardTrigger) {
        super(name, description, cost, rarity, cardGrave, CardType.POWER);
        this.cardTrigger = cardTrigger;
    }

    @Override
    public abstract void play(GameContext gameContext);

    /**
     * Was passiert wenn die Karte getriggert wird
     *
     * @param gameContext der gameContext
     */
    public abstract void ability(GameContext gameContext);

    /**
     * getter CardTrigger
     *
     * @return den Trigger
     */
    public CardTrigger getCardTrigger() {
        return cardTrigger;
    }
}