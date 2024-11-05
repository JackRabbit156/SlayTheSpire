package models.events.actone;

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
        super("You fall into a puddle.\n" +
                "IT'S MADE OF SLIME GOO!!\n" +
                "Frantically, you claw yourself out over several minutes as you feel the goop starting to burn.\n" +
                "You can feel goop in your ears, goop in your nose, goop everywhere.\n" +
                "Climbing out, you notice that some of your gold is missing. " +
                "Looking back to the puddle you see your missing coins combined with gold " +
                "from unfortunate adventurers mixed together in the puddle.", "World of Goo");
        this.player = player;
    }

    @Override
    public void startEvent() {
        int randGold;
        EventView.displayStory(getTitle(), getStory());
        //Die optionen sollen dem Spieler nicht erklärt werden.
        System.out.println("1. Gather Gold \n2. Leave it \n\nChoose an option: ");
        String input= scanner.next();
        switch(input){
            case "1":
                System.out.println("Feeling the sting of the goop as the prolonged " +
                        "exposure starts to melt away at your skin, you manage to fish out the gold.");
                player.increaseGold(75);
                player.setCurrentHealth(player.getCurrentHealth() - 11);
                System.out.println("You gained " + player.getGold() + " but lost 11 hp.");
                break;
            case "2":
                System.out.println("You decide that mess is not worth it.");
                randGold = (rand.nextInt(30) + 20) + 1;
                player.setGold(player.getGold() - randGold);
                System.out.println("You lost " + player.getGold() + " Gold.");
                break;
            default:
                System.out.println("Wrong input, try again...");
                startEvent();
        }
    }
}