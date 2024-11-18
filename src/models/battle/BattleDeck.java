package models.battle;

import controller.listener.BattleDeckListener;
import models.card.card_structure.Card;
import models.card.card_structure.PowerCard;

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
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> discardPile;
    private List<Card> exhaustPile;
    private List<PowerCard> currentPowerCards;
    private Random random;
    private int startHandSize;

    private BattleDeckListener battleDeckListener;

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
        this.random = new Random();
        this.startHandSize = 5;
        this.currentPowerCards = new ArrayList<>();
        createShuffledDeck();
    }

    public void setBattleDeckListener(BattleDeckListener battleDeckListener){
        this.battleDeckListener = battleDeckListener;
    }

    /**
     * Gibt die Handkarten zurück.
     *
     * @return Die Liste der Handkarten.
     */
    public List<Card> getHand() {
        return hand;
    }

    public void createShuffledDeck() {
        int randNum = this.random.nextInt(10);
        for (int i = 0; i <= randNum; i++) {
            Collections.shuffle(this.deck);
        }
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
            hand.add(deck.remove(deck.size() - 1)); //zieht von oben
        }
        battleDeckListener.onCardFill();
    }

    /**
     * Zieht eine bestimmte Anzahl von Karten und fügt sie der Hand hinzu.
     *
     * @param count Die Anzahl der zu ziehenden Karten.
     */
    public void drawCard(int count) {
        fillHand(hand.size() + count);
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
     * Exhaustet eine Karte von der Hand und fügt sie dem Exhaust-Stapel hinzu.
     *
     * @param card Die zu exilierende Karte.
     */
    public void exhaustCardFromHand(Card card) {
        exhaustPile.add(card);
        hand.remove(card);
    }

    /**
     * Entfernt eine Karte von der Hand.
     *
     * @param card Die zu entfernende Karte.
     */
    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    public void exhaustCardFromDeck(Card card) {
        exhaustPile.add(card);
        deck.remove(card);
    }

    public void removeCardFromDeck(Card card) {
        deck.remove(card);
    }

    public void resetDeckFromDiscardPile() {
        if (deck.isEmpty() && !discardPile.isEmpty()) {
            deck.addAll(discardPile);
            discardPile.clear();
        }
    }


    public int getStartHandSize() {
        return startHandSize;
    }

    public void addPowerCards(PowerCard powerCard) {
        currentPowerCards.add(powerCard);
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> getExhaustPile() {
        return exhaustPile;
    }

    public List<PowerCard> getCurrentPowerCards() {
        return currentPowerCards;
    }


}
