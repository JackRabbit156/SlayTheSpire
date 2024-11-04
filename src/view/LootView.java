package view;

import helper.ConsoleAssistent;
import models.cards.card_structure.Card;
import models.player.player_structure.Player;

import java.util.List;

/**
 * Lootauswahl Darstellung
 * @author Keil, Vladislav
 */
public class LootView {
    /**
     * Main Display - führt ein clearScreen aus und ziegt den Titel an.
     * @param cardsToChoice
     * @param gold
     */
    public void display(List<Card> cardsToChoice, int gold) {
        ConsoleAssistent.clearScreen();
        displayTitle();

        System.out.printf("You got %d. Gold! %n", gold);
        System.out.println();
        System.out.printf("%s %n", "Choose a Card!");
        displayCards(cardsToChoice);
        System.out.println();
        System.out.printf("%s %n", "Potions:");
        System.out.printf("%s %n", "Coming soon... This selection leads to the number 1");
        System.out.println();
        displayCardChoiceMenu(cardsToChoice.size());
    }

    public void displayCards(List<Card> cardsToChoice) {
        for (int i = 0; i < cardsToChoice.size(); i++) {
            System.out.printf("%2d. - %-10s %-5s %-15s%n", i + 1, cardsToChoice.get(i).getCardRarity(), cardsToChoice.get(i).getName(), cardsToChoice.get(i).getDescription());
        }
    }

    public void displayCardChoiceMenu(int shopCardAmount) {
        System.out.println(ConsoleAssistent.repeat(80, "-"));
        System.out.printf("%s %s %n", "(1-" + shopCardAmount + ") Choose Card", "(0) Leave Card Selection");
    }

    public void displayTitle() {
        System.out.println("\n" + ConsoleAssistent.repeat(80, "="));
        System.out.println(ConsoleAssistent.repeat(29, " ") + "<<<   LOOT VIEW   >>>                  ");
        System.out.println(ConsoleAssistent.repeat(80, "=") + "\n");
    }
}
