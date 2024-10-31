package models.cards.card_structure;

import models.GameContext;

public abstract class StatusCard extends Card {
    public StatusCard(String name, String description, int cost, CardRarity rarity, CardGrave cardGrave) {
        super(name, description, cost, rarity, cardGrave);
    }

    @Override
    public abstract void play(GameContext gameContext);

    public abstract int doStatus();
}