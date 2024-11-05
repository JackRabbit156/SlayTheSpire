package models.events.actone;

import models.events.Event;
import models.player.player_structure.Player;
import view.EventView;

import java.util.Scanner;
/**
 * Der Spieler kann 175 Gold erhalten.
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class TheSssssserpent extends Event {
    private Player player;
    private Scanner scanner = new Scanner(System.in);

    public TheSssssserpent(Player player) {
        super("You walk into a room to find a large hole in the ground. As you approach the hole, " +
                        "an enormous serpent creature appears from within.", "The Sssssserpent");
        this.player = player;
    }

    @Override
    public void startEvent() {
        String input = scanner.next();
        EventView.displayStory(getTitle(), getStory());
        //Dialog mit der Schlange
        System.out.println("Serpent: \"Ho hooo! Hello hello! what have we got here? Hello adventurer, I ask a simple question.\"\n" +
                "Serpent: \"The most fulfilling of lives is that in which you can buy anything!\"\n" +
                "Serpent: \"Do you agree?\" (Y/N)");
        if (input.toLowerCase().equals("y")) {
            System.out.println("Serpent: \"Yeeeeeeessssssssssessss\"\n" +
                    "Serpent: \"Thisss will all be worthhh it.\"\n" +
                    "Serpent: \"..ssSSs..... ss... sssss....!\"\n" +
                    "The serpent rears its head and blasts a stream of gold upwards!\n" +
                    "It is amazing and terrifying simultaneously.\n" +
                    "You gather all the gold, thank the snake, and get going.");
            //Der Spieler erhält 175 Gold
            player.increaseGold(175);
        }
        else if (input.toLowerCase().equals("n")) {
            System.out.println("The serpent stares at you with a look of extreme disappointment.");
        }
        else {
            System.out.println("Wrong input, please try again...");
        }
    }
}