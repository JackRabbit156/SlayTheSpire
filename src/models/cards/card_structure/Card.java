package models.cards.card_structure;

import models.GameContext;

import java.util.Random;

public abstract class Card {
    private Random rand = new Random();

    private String name;
    private String description;
    private int price;
    private int cost = 0;
    private CardRarity cardRarity;
    private CardGrave cardGrave;
    private CardType cardType;

    public Card(String name, String description, int cost, CardRarity cardRarity, CardGrave cardGrave, CardType cardType) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.cardRarity = cardRarity;
        this.cardGrave = cardGrave;
        this.price = genPrice();
        this.cardType = cardType;
    }

    public abstract void play(GameContext gameContext);

    public int getCost(){
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CardRarity getCardRarity() {
        return cardRarity;
    }

    public CardGrave getCardGrave() {
        return cardGrave;
    }

    public int getPrice() {
        return price;
    }

    public CardType getCardType() {
        return cardType;
    }

    public int genPrice() {
        // TODO - ShopController - One of the cards will always be On Sale, reducing its cost by 50%.
        // TODO - ShopController - There will always be 2 Attack cards, 2 Skill cards, and 1 Power card.
        // Existieren nur 3 Typen
        // https://slay-the-spire.fandom.com/wiki/Merchant
        switch (this.cardRarity) {
            case COMMON: {
                return rand.nextInt(55 + 1 - 45) + 45;
            }
            case UNCOMMON: {
                return rand.nextInt(82 + 1 - 68) + 68;
            }
            case RARE: {
                return rand.nextInt(165 + 1 - 135) + 135;
            }
            default: {
                return rand.nextInt(9);
            }
        }
    }
}