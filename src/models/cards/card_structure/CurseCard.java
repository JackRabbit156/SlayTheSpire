package models.cards.card_structure;

import models.GameContext;

public abstract class CurseCard extends Card {
    public CurseCard(String name, String description, int cost, CardRarity rarity) {
        super(name, description, cost, rarity);
    }

    @Override
    public abstract void play(GameContext gameContext);

    public abstract int doCurse();
}