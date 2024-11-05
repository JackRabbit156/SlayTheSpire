package view;

import helper.ConsoleAssistent;
import models.cards.card_structure.Card;
import models.player.player_structure.Player;

import java.util.List;

/**
 * Restsite Darstellung
 *
 * @author Keil, Vladislav
 */
public class RestSiteView {

    /**
     * Die Darstellung der Restsite / des Lagerfeuers.
     * @param player Player Anzeigen der "player" Attribute
     */
    public void display(Player player) {
        ConsoleAssistent.clearScreen();
        displayTitle();

        System.out.printf("%-20s %-10s%n", "Player:", player.getName());
        System.out.printf("%-20s %s / %s%n", "Health:", player.getCurrentHealth(), player.getMaxHealth());
        System.out.println();
        System.out.println("Your Options:");
        System.out.println("1 - Rest: Recover 30 percent of max health");
        System.out.println("2 - Upgrade: Upgrade a Card in your Deck (Coming soon...)");
        System.out.println("3 - Lift: Gain 1 Strength (Coming soon...)");
        System.out.println("    Coming soon Options refer to Rest.");
        System.out.println();
    }

    /**
     * Darstellung Titel
     */
    public void displayTitle() {
        System.out.println("\n" + ConsoleAssistent.repeat(80, "="));
        System.out.println(ConsoleAssistent.repeat(29, " ") + "<<<   CAMPFIRE VIEW   >>>                  ");
        System.out.println(ConsoleAssistent.repeat(80, "=") + "\n");
    }


    /**
     * Nicht Keine Funktion - Für die Zukunft
     * Anzeige der Upgradebare Karte im Deck
     *
     * @param deck eigenes Deck
     */
    public void displayUpgradeableCards(List<Card> deck) {
        for (int i = 0; i < deck.size(); i++) {
            System.out.printf("%d. %s %s%n", i + 1, deck.get(i).getName(), deck.get(i).getDescription());
        }
        System.out.printf("%n");
    }
}
