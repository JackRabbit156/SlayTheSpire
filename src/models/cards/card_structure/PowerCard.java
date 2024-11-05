package models.cards.card_structure;

import models.GameContext;

public abstract class PowerCard extends Card {
    CardTrigger cardTrigger;

    public PowerCard(String name, String description, int cost, CardRarity rarity, CardGrave cardGrave, CardTrigger cardTrigger) {
        super(name, description, cost, rarity, cardGrave, CardType.POWER);
        this.cardTrigger = cardTrigger;
    }

    @Override
    public abstract void play(GameContext gameContext);

    public abstract void ability(GameContext gameContext);

    public CardTrigger getCardTrigger() {
        return cardTrigger;
    }
}