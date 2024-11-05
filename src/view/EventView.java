package view;

import helper.ConsoleAssistent;
import models.player.player_structure.Player;

import java.util.List;

/**
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class EventView {

    /**
     * Stellt die Eventdarstellung dar.
     */
    public static void displayStory(String title, String eventStory) {
        displayTitle();
        System.out.println();
        System.out.printf("%-20s %s%n", "\tEvent:", title);
        System.out.printf("%-20s %s %n", "\tStory: \n", eventStory);
        System.out.println();
        System.out.println("Your Options:");
    }

    public static void displayOptions(List<String> eventOptions) {
        for (int i = 0; i < eventOptions.size(); i++) {
            System.out.printf("%d. %s %n", i + 1, eventOptions);
        }
        System.out.printf("%n");
    }

    public static void viewDeck(Player player){
        int displayInt;
        //Karten auflisten.
        for (int i = 0; i < player.getDeck().size(); i++) {
            displayInt = i + 1;
            System.out.println(displayInt + ". " + player.getDeck().get(i).toString() + "Rarity: " + player.getDeck().get(i).getCardRarity());
        }
    }

    public static void displayTitle() {
        System.out.println("\n" + ConsoleAssistent.repeat(80, "="));
        System.out.println(ConsoleAssistent.repeat(29, " ") + "<<<   EVENT VIEW   >>>                  ");
        System.out.println(ConsoleAssistent.repeat(80, "=") + "\n");
    }
}
