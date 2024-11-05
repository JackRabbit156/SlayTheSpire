package models.events.actone;

import helper.ConsoleAssistent;
import models.events.Event;
import models.player.player_structure.Player;
import view.EventView;

import java.util.Random;
import java.util.Scanner;
/**
 * Der Spieler erhält entweder Gold, oder verliert einen zufälligen Goldwert.
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class WorldOfGoo extends Event {
    private Player player;
    private Scanner scanner = new Scanner(System.in);
    private Random rand = new Random();


    public WorldOfGoo(Player player) {
        super("  You fall into a puddle.\n" +
                "\tIT'S MADE OF SLIME GOO!!\n" +
                "\tFrantically, you claw yourself out over several minutes as you feel the goo starting to burn.\n" +
                "\tYou can feel goop in your ears, goo in your nose, goo everywhere!\n" +
                "\tClimbing out, you notice that some of your gold is missing.\n " +
                "\tLooking back to the puddle you see your missing coins combined with gold \n" +
                "\tfrom unfortunate adventurers mixed together in the puddle.", "World of Goo");
        this.player = player;
    }

    @Override
    public void startEvent() {
        ConsoleAssistent.clearScreen();
        int randGold;
        EventView.displayHead(getTitle(), getStory());
        //Die optionen sollen dem Spieler nicht erklärt werden.
        System.out.println("\t1. Gather Gold \n\t2. Leave it \n\n");
        System.out.print("\tChoose an option: ");
        String input= scanner.next();
        switch(input){
            case "1":
                System.out.println("\n\tFeeling the sting of the goop as the prolonged " +
                        "\n\texposure starts to melt away at your skin, you manage to fish out the gold.");
                player.increaseGold(75);
                player.setCurrentHealth(player.getCurrentHealth() - 11);
                System.out.println("\n\tYou gained " + player.getGold() + " but lost 11 hp.");
                break;
            case "2":
                System.out.println("\n\tYou decide that mess is not worth it.");
                randGold = (rand.nextInt(30) + 20) + 1;
                player.setGold(player.getGold() - randGold);
                System.out.println("\n\tYou lost " + player.getGold() + " Gold.");
                break;
            default:
                System.out.println("\n\tWrong input, try again...");
                startEvent();
        }
    }
}