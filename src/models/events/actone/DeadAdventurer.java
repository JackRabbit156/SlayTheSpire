package models.events.actone;

import helper.ConsoleAssistent;
import models.events.Event;
import models.player.player_structure.Player;
import view.cli.EventView;

import java.util.Random;
import java.util.Scanner;
/**
 * Der Spieler kann mit einer bestimmten Wahrscheinlichkeit Gold finden
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class DeadAdventurer extends Event {
    private Player player;
    private Scanner scanner = new Scanner(System.in);
    private Random rand = new Random();

    public DeadAdventurer(Player player) {
        super("  You come across a dead adventurer on the floor.\n" +
                "\tHis pants have been stolen! Also, it looks as though he's been gouged and trampled by a horned beast. \n" +
                "\tThough his possessions are still intact, you're in no mind to find out what happened here...", "Dead Adventurer");
        this.player = player;
    }

    @Override
    public void startEvent() {
        ConsoleAssistent.clearScreen();
        int chance;
        String options;
        EventView.displayHead(getTitle(), getStory());
        chance = rand.nextInt(100);
        System.out.println("\t1. Search the body\n\t2. Leave\n\n");
        System.out.print("\tChoose an option: ");
        options = scanner.next();
        if (options.equals("1")) {
            if (chance >= 50) {
                System.out.println("\n\tYou found 30 Gold!");
                player.increaseGold(30);
            }
            else {
                System.out.println("\n\tHmm, couldn't find anything.");
                return;
            }
        }
        else if (options.equals("2")) {
            System.out.println("\n\tYou exit without a sound");
            return;
        }
        else {
            System.out.println("\n\tWrong input, please try agian...");
            startEvent();
        }
    }
}
