package de.bundeswehr.auf.slaythespire.model.card.structure;

import de.bundeswehr.auf.slaythespire.controller.listener.CardDeathListener;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.battle.Playable;

import java.util.Random;

public abstract class Card {

    private static final Playable defaultPlayable = new Playable();
    private static final Random rnd = new Random();

    private CardDeathListener cardDeathListener;
    private final CardGrave cardGrave;
    private final CardRarity cardRarity;
    private final CardType cardType;
    private final int cost;
    private final String description;
    private String imagePath;
    private final String name;
    private final int price;

    public Card(String name, String description, int cost, CardRarity cardRarity, CardGrave cardGrave, CardType cardType) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.cardRarity = cardRarity;
        this.cardGrave = cardGrave;
        this.price = generatePrice();
        this.cardType = cardType;
    }

    public void cancel() {
        cardDeathListener.onCardCanceled(this);
    }

    public CardGrave getCardGrave() {
        return cardGrave;
    }

    public CardRarity getCardRarity() {
        return cardRarity;
    }

    public CardType getCardType() {
        return cardType;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    /**
     * Every card is playable by default. Override to implement different behaviour.
     *
     * @param gameContext current {@link GameContext} to determine, if a card of this type is playable
     * @return if playable the default object will be returned, an object with error and log message, if not
     */
    public Playable isPlayable(GameContext gameContext) {
        return defaultPlayable;
    }

    /**
     * @param gameContext current {@link GameContext} to trigger the actions of this cards
     */
    public abstract void play(GameContext gameContext);

    public void played() {
        LoggingAssistant.log(name + " played", Color.PURPLE);
        cardDeathListener.onCardDeath(this);
    }

    public void register(CardDeathListener listener) {
        this.cardDeathListener = listener;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * @return price
     * @author Keil, Vladislav
     */
    private int generatePrice() {
        // Existieren nur 3 Typen
        // https://slay-the-spire.fandom.com/wiki/Merchant
        switch (this.cardRarity) {
            case COMMON: {
                return rnd.nextInt(55 + 1 - 45) + 45;
            }
            case UNCOMMON: {
                return rnd.nextInt(82 + 1 - 68) + 68;
            }
            case RARE: {
                return rnd.nextInt(165 + 1 - 135) + 135;
            }
            default: {
                return rnd.nextInt(9);
            }
        }
    }

}