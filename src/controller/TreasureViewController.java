package controller;

import helper.Color;
import helper.ConsoleAssistent;
import models.cards.DeckFactory;
import models.cards.card_structure.Card;
import models.game_settings.GameSettings;
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
        treasureView = new TreasureView();
        scanner = new Scanner(System.in);
        this.player = player;

        int amount = 5;
        switch (GameSettings.getDifficultyLevel()) {
            case EASY:
                amount = 5;
                break;
            case NORMAL:
                amount = 3;
                break;
            case HARD:
                amount = 1;
                break;
        }

        deckFactory = new DeckFactory(player, amount);
        purchasableCards = deckFactory.init();

        if (amount == 1) {
            player.addCardToDeck(purchasableCards.get(0));
            return;
        }
        cardChoice();
    }

    private void cardChoice() {
        ConsoleAssistent.clearScreen();
        treasureView.display(purchasableCards);
        String input = scanner.next();
        switch (input) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                int choose = Integer.parseInt(input);
                addCardToDeck(purchasableCards.get(choose - 1));
                break;
            case "0":
                return;
            default:
                ConsoleAssistent.print(Color.YELLOW, "Wrong Choice!");
                cardChoice();
                break;
        }
    }

    private void addCardToDeck(Card card) {
        player.addCardToDeck(card);
    }

}
