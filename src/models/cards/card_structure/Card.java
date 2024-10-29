package models.cards.card_structure;

import models.GameContext;

public abstract class Card {
    private String name;
    private String description;
    private int cost;
    private CardRarity cardRarity;

    public Card(String name, String description, int cost, CardRarity cardRarity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.cardRarity = cardRarity;
    }

    public abstract void play(GameContext gameContext);

    public int getCost(){
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public CardRarity getCardRarity(){
        return cardRarity;
    }
}