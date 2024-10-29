package view;

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

        //TODO Spielwährung fehlt
//        System.out.printf("%s %d.\n", "Character Coins:", player.getCoins());
        System.out.printf("%s \n", "Upgradeable-Card-Selection:");
        System.out.println(repeat(80, "-"));
//        System.out.printf("%" + 80 + "s %-20s\n", "Price:", shopCard.getPrice());
        for (int i = 0; i < shopCards.size(); i++) {
            //TODO shopCards benötigen einen Preis
            // Add %d. für den Preis
            System.out.printf("%2d. %-6s %-5s %-15s\n", i + 1, shopCards.get(i).getName(), shopCards.get(i).getCardRarity(), shopCards.get(i).getDescription());
        }
    }

    public void displayTitle() {
        System.out.println("\n" + repeat(80, "="));
        System.out.println(repeat(29, " ") + "<<<   SHOP VIEW   >>>                  ");
        System.out.println(repeat(80, "=") + "\n");
    }

    private String repeat(int length, String strToRepeat) {
        String returnValue = "";

        for (int i = 0; i < length; i++)
            returnValue += strToRepeat;

        return returnValue;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
