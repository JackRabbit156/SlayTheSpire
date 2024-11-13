package models.events.act_one;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.events.Event;
import models.player.player_structure.Player;
import view.cli.EventView;

import java.util.Scanner;
/**
 * Der Spieler kann 175 Gold erhalten.
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class TheSssssserpentEvent extends Event {
    private Player player;
    private Scanner scanner = new Scanner(System.in);

    public TheSssssserpentEvent(Player player) {
        super("  You walk into a room to find a large hole in the ground. " +
                "\n\tAs you approach the hole, an enormous serpent creature appears from within.", "The Sssssserpent");
        setImagePath(new PathAssistent().toPath(this));
        this.player = player;
    }

    @Override
    public void startEvent() {
        ConsoleAssistent.clearScreen();
        EventView.displayHead(getTitle(), getStory());
        //Dialog mit der Schlange
        System.out.println("\tSerpent: \"Ho hooo! Hello hello! what have we got here? \n" +
                "\tHello adventurer, I ask a simple question.\"\n" +
                "\tSerpent: \"The most fulfilling of lives is that in which you can buy anything!\"\n");
        System.out.print("\tSerpent: \"Do you agree?\" (Y/N) ");
        String input = scanner.next();
        if (input.toLowerCase().equals("y")) {
            System.out.println("\n\tSerpent: \"Yeeeeeeessssssssssessss\"\n" +
                    "\tSerpent: \"Thisss will all be worthhh it.\"\n" +
                    "\tSerpent: \"..ssSSs..... ss... sssss....!\"\n" +
                    "\tThe serpent rears its head and blasts a stream of gold upwards!\n" +
                    "\tIt is amazing and terrifying simultaneously.\n" +
                    "\tYou gather all the gold, thank the snake, and get going.\n");
            //Der Spieler erhält 175 Gold
            player.increaseGold(175);
        }
        else if (input.toLowerCase().equals("n")) {
            System.out.println("\tThe serpent stares at you with a look of extreme disappointment.");
        }
        else {
            System.out.println("\tWrong input, please try again...");
        }
    }
}