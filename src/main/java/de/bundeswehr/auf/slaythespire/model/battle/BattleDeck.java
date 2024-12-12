package de.bundeswehr.auf.slaythespire.model.battle;

import de.bundeswehr.auf.slaythespire.controller.listener.BattleDeckListener;
import de.bundeswehr.auf.slaythespire.gui.events.CardEvent;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.PowerCard;

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
public class BattleDeck {

    private static final Random rnd = new Random();

    private BattleDeckListener battleDeckListener;
    private final List<PowerCard> currentPowerCards;
    private final List<Card> deck;
    private final List<Card> discardPile;
    private final List<Card> exhaustPile;
    private final List<Card> hand;
    private final int startHandSize;

    /**
     * Konstruktor für die BattleDeck-Klasse.
     *
     * @param originalDeck Der Originalstapel, von dem eine Kopie erstellt wird.
     */
    public BattleDeck(List<Card> originalDeck) {
        this.deck = new ArrayList<>(originalDeck); // Create a copy of the deck of the player
        this.hand = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.exhaustPile = new ArrayList<>();
        this.startHandSize = 5;
        this.currentPowerCards = new ArrayList<>();
        createShuffledDeck();
    }

    public void addToDeck(Card card) {
        deck.add(card);
        battleDeckListener.onCardFill();
    }

    public void addPowerCards(PowerCard powerCard) {
        currentPowerCards.add(powerCard);
    }

    public void chooseCardFromDiscardPile(CardEvent event) {
        List<Card> cards = new ArrayList<>(discardPile);
        cards.sort((o1, o2) -> rnd.nextInt(3) - 1);
        battleDeckListener.chooseCard(cards, event);
    }

    public void chooseCardFromHand(CardEvent event) {
        List<Card> cards = new ArrayList<>();
        for (Card card : hand) {
            if (card != event) {
                cards.add(card);
            }
        }
        battleDeckListener.chooseCard(cards, event);
    }

    public void createShuffledDeck() {
        int randNum = rnd.nextInt(10);
        for (int i = 0; i <= randNum; i++) {
            Collections.shuffle(this.deck);
        }
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
     * @param count Die Anzahl der zu ziehenden Karten.
     */
    public void drawCard(int count) {
        fillHand(hand.size() + count);
    }

    public void exhaustCardFromDeck(Card card) {
        exhaustPile.add(card);
        deck.remove(card);
    }

    /**
     * Exhaustet eine Karte von der Hand und fügt sie dem Exhaust-Stapel hinzu.
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
            hand.add(deck.remove(deck.size() - 1)); // zieht von oben
        }
        battleDeckListener.onCardFill();
    }

    public List<PowerCard> getCurrentPowerCards() {
        return currentPowerCards;
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

    public void removeCardFromDeck(Card card) {
        deck.remove(card);
    }

    /**
     * Entfernt eine Karte aus dem DiscardPile.
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

    public void resetDeckFromDiscardPile() {
        if (deck.isEmpty() && !discardPile.isEmpty()) {
            deck.addAll(discardPile);
            discardPile.clear();
        }
    }

    public void setBattleDeckListener(BattleDeckListener battleDeckListener) {
        this.battleDeckListener = battleDeckListener;
    }


}
