package models.cards.card_structure;

import models.GameContext;

public abstract class PowerCard extends Card {
    public PowerCard(String name, String description, int cost, CardRarity rarity, boolean singleTarget) {
        super(name, description, cost, rarity);
    }

    @Override
    public abstract void play(GameContext gameContext);

    public abstract int doPowerThing();
}