package view;

//TODO Import Shop Class

import models.cards.card_structure.Card;
import models.player.player_structure.Player;

import java.util.List;

/**
 * Campfire Darstellung
 * @author Keil, Vladislav
 */
public class CampfireView {

    /**
     *
     * @param player Spieler zur Anzeige von
     */
    public void display(Player player) {
        displayTitle();

        System.out.printf("%-20s %-10s%n", "Player:", player.getName());
        System.out.printf("%-20s %s / %s%n", "Health:", player.getCurrentHealth(), player.getMaxHealth());
        System.out.println();
        System.out.println("Your Options:");
        System.out.println("1 - Rest: Recover 30 percent of max health");
        System.out.println("2 - Upgrade: Upgrade a Cards in your Deck");
        System.out.println();
    }

    public void displayTitle() {
        System.out.println("\n" + repeat(80, "="));
        System.out.println(repeat(29, " ") + "<<<   CAMPFIRE VIEW   >>>                  ");
        System.out.println(repeat(80, "=") + "\n");
    }

    //TODO Erstellung der Upgradefunktion für das Deck
    /**
     * Anzeige der Upgradebare Karte im Deck
     * @param deck eigenes Deck
     */
    public void displayUpgradeableCards(List<Card> deck) {
        for (int i = 0; i < deck.size(); i++) {
            System.out.printf("%d. %s %s%n", i + 1, deck.get(i).getName(), deck.get(i).getDescription());
        }
        System.out.printf("%n");
    }

    private String repeat(int length, String strToRepeat) {
        StringBuilder returnValue = new StringBuilder("");

        for (int i = 0; i < length; i++)
            returnValue.append(strToRepeat);

        return returnValue.toString();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
