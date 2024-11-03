package view;

import helper.ConsoleAssistent;
import models.cards.card_structure.Card;
import models.player.player_structure.Player;

import java.util.List;

/**
 * @author Keil, Vladislav
 * @author Loeschner, Marijan
 */
public class TreasureView {

    public void display(Player player, List<Card> availableCards) {
        displayTitle();

        System.out.printf("%s %n", "Treasure-Cards:");
        System.out.println(ConsoleAssistent.repeat(80, "-"));
        displayCards(availableCards);
        System.out.println();
    }


    public void displayCards(List<Card> availableCards) {
        for (int i = 0; i < availableCards.size(); i++) {
            System.out.printf("%2d. - %-10s %-5s %-15s%n", i + 1, availableCards.get(i).getCardRarity(), availableCards.get(i).getName(), availableCards.get(i).getDescription());
        }
    }

    public void displayCardChoiceMenu(int shopCardAmount) {
        System.out.println(ConsoleAssistent.repeat(80, "-"));
        System.out.printf("%s %n", "(1-" + shopCardAmount + ") Choose Card");
    }

    public void displayTitle() {
        System.out.println("\n" + ConsoleAssistent.repeat(80, "="));
        System.out.println(ConsoleAssistent.repeat(29, " ") + "<<<   TREASURE VIEW   >>>                  ");
        System.out.println(ConsoleAssistent.repeat(80, "=") + "\n");
    }
}
