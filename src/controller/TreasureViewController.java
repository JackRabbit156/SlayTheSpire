package controller;

import helper.Color;
import helper.ConsoleAssistent;
import models.cards.DeckFactory;
import models.cards.card_structure.Card;
import models.player.player_structure.Player;
import view.TreasureView;

import java.util.List;
import java.util.Scanner;

/**
 * @author Keil, Vladislav
 * @author Loeschner, Marijan
 */
public class TreasureViewController {
    private Player player;
    private List<Card> purchasableCards;
    private Scanner scanner;
    private DeckFactory deckFactory;
    private TreasureView treasureView;

    public TreasureViewController(Player player) {
        deckFactory = new DeckFactory(player, 5);
        purchasableCards = deckFactory.init();
        treasureView = new TreasureView();
        cardChoice();
    }


    private void cardChoice() {
        ConsoleAssistent.clearScreen();
        treasureView.displayCards(purchasableCards);
        treasureView.displayCardChoiceMenu(purchasableCards.size());

        int input = scanner.nextInt();
        // TODO Input-Validator
        if (input > purchasableCards.size()) {
            ConsoleAssistent.print(Color.YELLOW, "Wrong Choice!");
            cardChoice();
        }

        switch (input) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                addCardToDeck(purchasableCards.get(input - 1));
                break;
            case 0:
                cardChoice();  //
        }
    }

    private void addCardToDeck(Card card) {
        player.addCardToDeck(card);
    }

}
