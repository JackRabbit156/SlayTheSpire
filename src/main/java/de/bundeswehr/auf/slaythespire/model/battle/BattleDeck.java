package de.bundeswehr.auf.slaythespire.model.battle;

import de.bundeswehr.auf.slaythespire.controller.listener.BattleDeckListener;
import de.bundeswehr.auf.slaythespire.gui.events.CardEventListener;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.PowerCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.TriggeredCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Diese Klasse repräsentiert das BattleDeck, das die Kartenverwaltung
 * während eines Kampfes organisiert. Es enthält Methoden zum Ziehen,
 * Entsorgen und Austauschen von Karten sowie zur Verwaltung der Handkarten
 * und der verschiedenen Stapel (Deck, Ablagestapel, Exil).
 *
 * @author Warawa Alexander, Willig Daniel
 */
public class BattleDeck implements CardEventListener {

    private static final Random rnd = new Random();

    private final List<BattleDeckListener> battleDeckListeners = new ArrayList<>();
    private final List<Card> drawPile;
    private final List<Card> discardPile;
    private final List<Card> exhaustPile;
    private final List<Card> hand;
    private final int startHandSize;
    private final List<TriggeredCard> triggeredCards;

    /**
     * Konstruktor für die BattleDeck-Klasse.
     *
     * @param deck Der Originalstapel, von dem eine Kopie erstellt wird.
     */
    public BattleDeck(List<Card> deck) {
        this.drawPile = DeckFactory.copy(deck); // Create a copy of the deck of the player
        this.hand = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.exhaustPile = new ArrayList<>();
        this.startHandSize = 5;
        this.triggeredCards = new ArrayList<>();
        createShuffledDeck();
    }

    public void addBattleDeckListener(BattleDeckListener battleDeckListener) {
        battleDeckListeners.add(battleDeckListener);
    }

    public void addToDrawPile(Card card) {
        drawPile.add(card);
        for (BattleDeckListener battleDeckListener : battleDeckListeners) {
            battleDeckListener.onCardFill();
        }
    }

    public void addToDiscardPile(Card card) {
        discardPile.add(card);
        for (BattleDeckListener battleDeckListener : battleDeckListeners) {
            battleDeckListener.onCardFill();
        }
    }

    public void addToHand(Card card) {
        for (BattleDeckListener battleDeckListener : battleDeckListeners) {
            battleDeckListener.onCardDrawn(card);
        }
        hand.add(card);
        for (BattleDeckListener battleDeckListener : battleDeckListeners) {
            battleDeckListener.onCardFill();
        }
    }

    public void addTriggeredCard(TriggeredCard card) {
        triggeredCards.add(card);
    }

    public void chooseCardFromDiscardPile(CardEventListener cardEventListener) {
        List<Card> cards = new ArrayList<>(discardPile);
        shuffle(cards);
        for (BattleDeckListener battleDeckListener : battleDeckListeners) {
            battleDeckListener.chooseCard(cards, cardEventListener);
        }
    }

    public void chooseCardFromHand(CardEventListener cardEventListener) {
        List<Card> cards = new ArrayList<>();
        for (Card card : hand) {
            if (card != cardEventListener) {
                cards.add(card);
            }
        }
        for (BattleDeckListener battleDeckListener : battleDeckListeners) {
            battleDeckListener.chooseCard(cards, cardEventListener);
        }
    }

    public void createShuffledDeck() {
        int randNum = rnd.nextInt(10);
        for (int i = 0; i <= randNum; i++) {
            Collections.shuffle(this.drawPile);
        }
    }

    /**
     * Entsorgt eine Karte aus dem Abhebestapel und fügt sie dem Ablagestapel hinzu.
     *
     * @param card Die zu entsorgende Karte.
     */
    public void discardCardFromDrawPile(Card card) {
        discardPile.add(card);
        drawPile.remove(card);
    }

    /**
     * Entsorgt eine Karte von der Hand und fügt sie dem Ablagestapel hinzu.
     *
     * @param card Die zu entsorgende Karte.
     */
    public void discardCardFromHand(Card card) {
        discardPile.add(card);
        hand.remove(card);
        for (BattleDeckListener battleDeckListener : battleDeckListeners) {
            battleDeckListener.onCardFill();
        }
    }

    /**
     * Zieht eine bestimmte Anzahl von Karten und fügt sie der Hand hinzu.
     *
     * @param amount Die Anzahl der zu ziehenden Karten.
     */
    public void drawCard(int amount) {
        fillHand(hand.size() + amount);
    }

    /**
     * Exiliert eine Karte aus dem Abhebestapel und fügt die sem Exhaust-Stapel hinzu.
     *
     * @param card Die zu exilierende Karte
     */
    public void exhaustCardFromDrawPile(Card card) {
        exhaustPile.add(card);
        drawPile.remove(card);
    }

    /**
     * Exiliert eine Karte von der Hand und fügt sie dem Exhaust-Stapel hinzu.
     *
     * @param card Die zu exilierende Karte.
     */
    public void exhaustCardFromHand(Card card) {
        exhaustPile.add(card);
        hand.remove(card);
    }

    /**
     * Füllt die Handkarten bis zur angegebenen Anzahl auf.
     *
     * @param count Die Anzahl der Karten, die die Hand erreichen soll.
     */
    public void fillHand(int count) {
        while (hand.size() < count) {
            resetDrawPileFromDiscardPile();
            if (drawPile.isEmpty()) {
                break;
            }
            Card card = drawPile.remove(drawPile.size() - 1); // zieht von oben
            for (BattleDeckListener battleDeckListener : battleDeckListeners) {
                battleDeckListener.onCardDrawn(card);
            }
            hand.add(card);
        }
        for (BattleDeckListener battleDeckListener : battleDeckListeners) {
            battleDeckListener.onCardFill();
        }
    }

    public List<Card> getDrawPile() {
        return drawPile;
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }

    public List<Card> getExhaustPile() {
        return exhaustPile;
    }

    /**
     * Gibt die Handkarten zurück.
     *
     * @return Die Liste der Handkarten.
     */
    public List<Card> getHand() {
        return hand;
    }

    public int getStartHandSize() {
        return startHandSize;
    }

    public List<Card> getTopFromDrawPile(int amount) {
        List<Card> cards = new ArrayList<>();
        if (drawPile.size() < amount) {
            resetDrawPileFromDiscardPile();
        }
        int lowerBound = Math.max(0, drawPile.size() - amount);
        for (int i = drawPile.size() - 1; i >= lowerBound; i--) {
            cards.add(drawPile.get(i));
        }
        return cards;
    }

    public List<TriggeredCard> getTriggeredCards() {
        return triggeredCards;
    }

    @Override
    public void onCardClick(Card card) {
        for (BattleDeckListener battleDeckListener : battleDeckListeners) {
            battleDeckListener.onCardClick(card);
        }
    }

    public void removeBattleDeckListener(BattleDeckListener battleDeckListener) {
        battleDeckListeners.remove(battleDeckListener);
    }

    /**
     * Entfernt eine Karte aus dem Abhebestapel
     *
     * @param card Die zu entfernende Karte
     */
    public void removeCardFromDrawPile(Card card) {
        drawPile.remove(card);
    }

    /**
     * Entfernt eine Karte aus dem Ablegestapel.
     *
     * @param card Die zu entfernende Karte.
     */
    public void removeCardFromDiscardPile(Card card) {
        discardPile.remove(card);
    }

    /**
     * Entfernt eine Karte von der Hand.
     *
     * @param card Die zu entfernende Karte.
     */
    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    public void removeNonPowerCards() {
        for (int i = triggeredCards.size() - 1; i >= 0; i--) {
            if (!(triggeredCards.get(i) instanceof PowerCard)) {
                triggeredCards.remove(i);
            }
        }
    }

    public void shuffleDrawPile() {
        shuffle(drawPile);
    }

    public void shuffleInDrawPile(Card card) {
        drawPile.add(rnd.nextInt(drawPile.size() + 1), card);
    }

    @Override
    public String toString() {
        return "BattleDeck\n" +
                "drawPile=" + drawPile + "\n" +
                "hand=" + hand + "\n" +
                "discardPile=" + discardPile + "\n" +
                "exhaustPile=" + exhaustPile + "\n" +
                "triggeredCards=" + triggeredCards;
    }

    /**
     * Fülle den Abhebestapel durch die abgelegten Karten.
     */
    private void resetDrawPileFromDiscardPile() {
        if (drawPile.isEmpty() && !discardPile.isEmpty()) {
            drawPile.addAll(discardPile);
            discardPile.clear();
            shuffleDrawPile();
        }
    }

    private void shuffle(List<Card> cards) {
        cards.sort((o1, o2) -> rnd.nextInt(3) - 1);
    }

}
