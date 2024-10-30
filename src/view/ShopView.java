package view;

import helper.ConsoleAssistent;
import models.cards.card_structure.Card;
import models.player.player_structure.Player;

import java.util.List;


/**
 * Shop Darstellung
 * @author Keil, Vladislav
 */
public class ShopView {

    /**
     * Stellt die Shopdarstellung dar.
     */
    public void display(Player player, List<Card> shopCards) {
        displayTitle();

        System.out.printf("%s %d.%n", "Character Coins:", player.getGold());
        System.out.printf("%s %n", "Upgradeable-Card-Selection:");
        System.out.println(ConsoleAssistent.repeat(80, "-"));
        for (int i = 0; i < shopCards.size(); i++) {
            System.out.printf("%2d. %-6s %3d. - %-6s %-5s %-15s%n", i + 1, "Price:", shopCards.get(i).getPrice(), shopCards.get(i).getName(), shopCards.get(i).getCardRarity(), shopCards.get(i).getDescription());
        }
    }

    public void displayTitle() {
        System.out.println("\n" + ConsoleAssistent.repeat(80, "="));
        System.out.println(ConsoleAssistent.repeat(29, " ") + "<<<   SHOP VIEW   >>>                  ");
        System.out.println(ConsoleAssistent.repeat(80, "=") + "\n");
    }
}
