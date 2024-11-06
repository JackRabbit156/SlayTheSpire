package controller;

import helper.Color;
import helper.ConsoleAssistent;
import models.cards.DeckFactory;
import models.cards.card_structure.Card;
import models.game_settings.GameSettings;
import models.player.player_structure.Player;
import view.TreasureView;

import java.util.InputMismatchException;
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
            case SUPEREASY:
            case EASY:
                amount = 5;
                break;
            case NORMAL:
                amount = 3;
                break;
            case IMPOSSIBLE:
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
        int input = 0;
        while(true){
            treasureView.display(purchasableCards);
            try{
                System.out.print(">> ");
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                ConsoleAssistent.print(Color.YELLOW, "Wrong input...");
            }
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
                return;
            default:
                ConsoleAssistent.print(Color.YELLOW, "Wrong Choice...");
                cardChoice();
                break;
        }
    }

    private void addCardToDeck(Card card) {
        player.addCardToDeck(card);
    }

}
