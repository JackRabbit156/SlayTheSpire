package de.bundeswehr.auf.slaythespire.model.card.structure;

public abstract class StatusCard extends Card {

    public StatusCard(String name, String description, int cost, CardRarity rarity, CardGrave cardGrave) {
        super(name, description, cost, rarity, cardGrave);
    }

    public abstract int doStatus();

}