package view;

import helper.ConsoleAssistent;
import models.events.actone.WorldOfGoo;
import models.player.player_structure.Player;

import java.util.List;

/** klasse für die Darstellung der Events
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class EventView {

    /**
     * Stellt den kopf der Eventview dar.
     * @param title title of the text
     * @param eventStory text of the event
     */
    public static void displayHead(String title, String eventStory) {
        displayTitle();
        System.out.println();
        System.out.printf("%-20s %s%n", " ", title + "\n");
        System.out.printf("%s %s %n", " ", eventStory);
        System.out.println();
        System.out.println("\tYour Options:");
    }

    public static void displayStory(List<String> eventOptions) {
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
            System.out.println("\t" + displayInt + ". " + player.getDeck().get(i).getName() + "Rarity: " + player.getDeck().get(i).getCardRarity());
        }
    }

    public static void displayTitle() {
        System.out.println("\n" + ConsoleAssistent.repeat(80, "="));
        System.out.println(ConsoleAssistent.repeat(29, " ") + "<<<   EVENT VIEW   >>>                  ");
        System.out.println(ConsoleAssistent.repeat(80, "=") + "\n");
    }
}
