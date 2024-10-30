package models;

import models.cards.card_structure.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleDeck {
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> discardPile;
    private Random random;

    public BattleDeck(List<Card> originalDeck) {
        this.deck = new ArrayList<>(originalDeck); // Create a copy of the deck of the player
        this.hand = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.random = new Random();
    }

    public List<Card> getHand() {
        return hand;
    }

    public void drawCards(int count) {
        //hand.clear();

        while(hand.size() != 5 ) {
            resetDeckFromDiscardPile();
            int randomIndex = random.nextInt(deck.size());
            hand.add(deck.remove(randomIndex));
        }
    }

    public void discardCard(Card card) {
        discardPile.add(card);
        hand.remove(card);
    }

    public void resetDeckFromDiscardPile() {
        if (deck.isEmpty() && !discardPile.isEmpty()) {
            deck.addAll(discardPile);
            discardPile.clear();
        }
    }
}
