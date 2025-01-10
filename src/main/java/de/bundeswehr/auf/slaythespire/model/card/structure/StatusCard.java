package de.bundeswehr.auf.slaythespire.model.card.structure;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;

public abstract class StatusCard extends Card {

    public StatusCard(String name, String description, int cost, CardGrave cardGrave) {
        super(name, description, cost, CardRarity.SPECIAL, cardGrave);
    }

    public void onDraw(GameContext gameContext) {}

}