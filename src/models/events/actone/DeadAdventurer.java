package models.events.actone;

import models.events.Event;
import models.player.player_structure.Player;
import view.EventView;

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
        super("You come across a dead adventurer on the floor.\n" +
                "His pants have been stolen! Also, it looks as though he's been gouged and trampled by a horned beast. " +
                "Though his possessions are still intact, you're in no mind to find out what happened here...", "Dead Adventurer");
        this.player = player;
    }

    @Override
    public void startEvent() {
        int chance;
        int options;
        EventView.displayStory(getTitle(), getStory());
        chance = rand.nextInt(100);
        System.out.println("1. Search the body\n2. Leave\n\nChoose an option: ");
        options = scanner.nextInt();
        if (options == 1) {
            if (chance >= 50) {
                System.out.println("You found 30 Gold!");
                player.increaseGold(30);
            }
            else {
                System.out.println("Hmm, couldn't find anything.");
                return;
            }
        }
        else if (options == 2) {
            System.out.println("You exit without a sound");
            return;
        }
        else {
            System.out.println("Wrong input, please try agian...");
            startEvent();
        }
    }
}
