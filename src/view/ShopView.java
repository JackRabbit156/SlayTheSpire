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
     * Darstellung des Shops
     * @param player    zur Darstellung des eigenen Golds.
     * @param shopCards zur Darstellung der Shop-Karten.
     */
    public void display(Player player, List<Card> shopCards) {
        ConsoleAssistent.clearScreen();
        displayTitle();

        System.out.printf("%s %d.%n", "Character Gold:", player.getGold());

        System.out.printf("%s %n", "1. Purchasable-Cards:");
        System.out.println(ConsoleAssistent.repeat(80, "-"));
        displayCards(shopCards);

        System.out.println();
        System.out.printf("%s %n", "2. Purchasable-Potions:");
        System.out.printf("%s %n", "Coming soon... This selection leads to the number 1");
        System.out.println();
        System.out.printf("%s %n", "3. Purchasable-Relics:");
        System.out.printf("%s %n", "Coming soon... This selection leads to the number 1");
        System.out.println();
    }

    /**
     * Darstellung der auswählbaren Karten.
     * @param shopCards     List<Card> auswählbare Karten
     */
    public void displayCards(List<Card> shopCards) {
        for (int i = 0; i < shopCards.size(); i++) {
            System.out.printf("%2d. %-5s %3d - %-10s %-5s %-15s%n", i + 1, "Price:", shopCards.get(i).getPrice(), shopCards.get(i).getCardRarity(), shopCards.get(i).getName(), shopCards.get(i).getDescription());
        }
    }

    /**
     * Darstellung des auswählbaren Karten oder für's verlassen.
     * @param shopCardAmount    int 1 - [ Anzahl der möglichen auswählbaren Karten ]
     */
    public void displayCardChoiceMenu(int shopCardAmount) {
        System.out.println(ConsoleAssistent.repeat(80, "-"));
        System.out.printf("%s %s %n", "(1-" + shopCardAmount + ") Choose Card", "(0) Leave Card Selection");
    }

    /**
     * Darstellung der auswählbaren Optionen.
     */
    public void displayOptionChoiceMenu() {
        System.out.println(ConsoleAssistent.repeat(80, "-"));
        System.out.printf("%s %s %n", "(1-3) Choose ItemType", "(0) Leave shop");
    }

    /**
     * Darstellung Titel
     */
    public void displayTitle() {
        System.out.println("\n" + ConsoleAssistent.repeat(80, "="));
        System.out.println(ConsoleAssistent.repeat(29, " ") + "<<<   SHOP VIEW   >>>                  ");
        System.out.println(ConsoleAssistent.repeat(80, "=") + "\n");
    }
}
