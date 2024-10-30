package models;

import models.cards.card_structure.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BattleDeck {
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> discardPile;
    private List<Card> exhaustPile;
    private Random random;
    private int startHandSize;

    public BattleDeck(List<Card> originalDeck) {
        this.deck = new ArrayList<>(originalDeck); // Create a copy of the deck of the player
        this.hand = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.exhaustPile = new ArrayList<>();
        this.random = new Random();
        this.startHandSize = 5;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void createShuffledDeck() {
        Collections.shuffle(deck);
    }

    public void fillHand(int count) {
        //hand.clear();

        while (hand.size() != count) {
            resetDeckFromDiscardPile();
            if (deck.isEmpty()) {
                break;
            }
            hand.add(deck.remove(deck.size() - 1)); //zieht von oben
        }

    }

    public void drawCard(int count) {
        fillHand(hand.size() + count);
    }

    public void discardCardFromHand(Card card) {
        discardPile.add(card);
        hand.remove(card);
    }

    public void exhaustCardFromHand(Card card) {
        exhaustPile.add(card);
        hand.remove(card);
    }

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


}
