package de.bundeswehr.auf.slaythespire.model.card.structure;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;

public abstract class StatusCard extends Card {
    public StatusCard(String name, String description, int cost, CardRarity rarity, CardGrave cardGrave) {
        super(name, description, cost, rarity, cardGrave, CardType.STATUS);
    }

    @Override
    public abstract void play(GameContext gameContext);

    public abstract int doStatus();
}