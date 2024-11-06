package models.events.actone;

import helper.ConsoleAssistent;
import models.events.Event;
import models.player.player_structure.Player;
import view.EventView;

import java.util.Scanner;
/**
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class BigFish extends Event {
    private Player player;
    private Scanner scanner = new Scanner(System.in);

    public BigFish(Player player) {
        super("  As you make your way down a long corridor you see a banana, a donut, and a box floating about. \n" +
                "\tNo... upon closer inspection they are tied to strings coming from holes in the ceiling. \n" +
                "\tThere is a quiet cackling from above as you approach the objects.\n" +
                "\tWhat do you do?", "Big Fish");
        this.player = player;
    }

    @Override
    public void startEvent() {
        ConsoleAssistent.clearScreen();
        EventView.displayHead(getTitle(), getStory());
        System.out.println("1. Banana\n2. Donut\n");
        System.out.print("\tChoose an option: ");
        String input = scanner.next();
        switch (input){
            case "1":
                System.out.println("\tYou eat the banana. It is nutritious and slightly magical, healing you.\n");
                player.setCurrentHealth(player.getCurrentHealth() + (player.getMaxHealth() /3));
                System.out.println("\tYou regained helath from this nutritious banana.\nYour hp are: " + player.getCurrentHealth());
                break;
            case "2":
                System.out.println("\tYou eat the donut. It really hits the spot! Your Max HP increases.\n");
                player.increaseMaxHealth(5);
                System.out.println("\tThe delicious donut you chose got your spirits fired up.\nYour maximum health increased by 5.\n");
                break;
            default:
                System.out.println("\tWrong input, try again...");
                startEvent();
        }
    }
}
