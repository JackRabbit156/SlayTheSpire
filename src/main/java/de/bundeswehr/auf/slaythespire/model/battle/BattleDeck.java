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

    private BattleDeckListener battleDeckListener;
    private final List<Card> deck;
    private final List<Card> discardPile;
    private final List<Card> exhaustPile;
    private final List<Card> hand;
    private final int startHandSize;
    private final List<TriggeredCard> triggeredCards;

    /**
     * Konstruktor für die BattleDeck-Klasse.
     *
     * @param originalDeck Der Originalstapel, von dem eine Kopie erstellt wird.
     */
    public BattleDeck(List<Card> originalDeck) {
        this.deck = DeckFactory.copy(originalDeck); // Create a copy of the deck of the player
        this.hand = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.exhaustPile = new ArrayList<>();
        this.startHandSize = 5;
        this.triggeredCards = new ArrayList<>();
        createShuffledDeck();
    }

    public void addToDeck(Card card) {
        deck.add(card);
        battleDeckListener.onCardFill();
    }

    public void addToDiscardPile(Card card) {
        discardPile.add(card);
    }

    public void addTriggeredCard(TriggeredCard card) {
        triggeredCards.add(card);
    }

    public void chooseCardFromDiscardPile(CardEventListener cardEventListener) {
        List<Card> cards = new ArrayList<>(discardPile);
        shuffle(cards);
        battleDeckListener.chooseCard(cards, cardEventListener);
    }

    public void chooseCardFromHand(CardEventListener cardEventListener) {
        List<Card> cards = new ArrayList<>();
        for (Card card : hand) {
            if (card != cardEventListener) {
                cards.add(card);
            }
        }
        battleDeckListener.chooseCard(cards, cardEventListener);
    }

    public void createShuffledDeck() {
        int randNum = rnd.nextInt(10);
        for (int i = 0; i <= randNum; i++) {
            Collections.shuffle(this.deck);
        }
    }

    /**
     * Entsorgt eine Karte aus dem Abhebestapel und fügt sie dem Ablagestapel hinzu.
     *
     * @param card Die zu entsorgende Karte.
     */
    public void discardCardFromDeck(Card card) {
        discardPile.add(card);
        deck.remove(card);
    }

    /**
     * Entsorgt eine Karte von der Hand und fügt sie dem Ablagestapel hinzu.
     *
     * @param card Die zu entsorgende Karte.
     */
    public void discardCardFromHand(Card card) {
        discardPile.add(card);
        hand.remove(card);
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
    public void exhaustCardFromDeck(Card card) {
        exhaustPile.add(card);
        deck.remove(card);
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
        //hand.clear();

        while (hand.size() != count) {
            resetDeckFromDiscardPile();
            if (deck.isEmpty()) {
                break;
            }
            Card card = deck.remove(deck.size() - 1); // zieht von oben
            battleDeckListener.onCardDrawn(card);
            hand.add(card);
        }
        battleDeckListener.onCardFill();
    }

    public List<Card> getDeck() {
        return deck;
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

    public List<Card> getTopFromDeck(int amount) {
        List<Card> cards = new ArrayList<>();
        if (deck.size() < amount) {
            resetDeckFromDiscardPile();
        }
        int lowerBound = Math.max(0, deck.size() - amount);
        for (int i = deck.size() - 1; i >= lowerBound; i--) {
            cards.add(deck.get(i));
        }
        return cards;
    }

    public List<TriggeredCard> getTriggeredCards() {
        return triggeredCards;
    }

    @Override
    public void onCardClick(Card card) {
        battleDeckListener.onCardClick(card);
    }

    /**
     * Entfernt eine Karte aus dem Abhebestapel
     *
     * @param card Die zu entfernende Karte
     */
    public void removeCardFromDeck(Card card) {
        deck.remove(card);
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

    public void setBattleDeckListener(BattleDeckListener battleDeckListener) {
        this.battleDeckListener = battleDeckListener;
    }

    public void shuffleDeck() {
        shuffle(deck);
    }

    public void shuffleInDrawPile(Card card) {
        deck.add(rnd.nextInt(deck.size() + 1), card);
    }

    @Override
    public String toString() {
        return "BattleDeck\n" +
                "deck=" + deck + "\n" +
                "hand=" + hand + "\n" +
                "discardPile=" + discardPile + "\n" +
                "exhaustPile=" + exhaustPile + "\n" +
                "triggeredCards=" + triggeredCards;
    }

    /**
     * Fülle den Abhebestapel durch die abgelegten Karten.
     */
    private void resetDeckFromDiscardPile() {
        if (deck.isEmpty() && !discardPile.isEmpty()) {
            deck.addAll(discardPile);
            discardPile.clear();
            shuffleDeck();
        }
    }

    private void shuffle(List<Card> cards) {
        cards.sort((o1, o2) -> rnd.nextInt(3) - 1);
    }

}
