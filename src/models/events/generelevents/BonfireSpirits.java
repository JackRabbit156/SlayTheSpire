package models.events.generelevents;

import helper.ConsoleAssistent;
import models.cards.card_structure.Card;
import models.events.Event;
import models.player.player_structure.Player;
import view.EventView;

import java.util.Scanner;

/**
 * Spieler kann eine Karte je nach Rarität für eine gegenleistung eintauschen.
 * @author Keil, Vladislav
 * @author Loeschner, marijan
 */
public class BonfireSpirits extends Event {
    private Player player;
    private Scanner scanner = new Scanner(System.in);

    public BonfireSpirits(Player player) {
        super("  You happen to stumble upon a group of what looks like purple fire spirits dancing around a large bonfire.\n" +
                "\tThe spirits toss small bones and fragments into the fire, which brilliantly erupts each time. " +
                "\n\tAs you approach, the spirits all turn to you, expectantly...", "BonfireSpirits");

        this.player = player;
    }

    /*  Der Plan:
        1. Eine Karte aus dem Deck kann geopfert werden. Dafür bekommt man je nach Rarität bestimmte Boni. siehe Issue STS-034
        2. Der Spieler kann auswählen nichts zu tun
         */

    @Override
    public void startEvent() {
        ConsoleAssistent.clearScreen();
        Card chosenCard;
        EventView.displayHead(getTitle(), getStory());
        EventView.viewDeck(player);
        System.out.print("\tDo you want to offer a Card to the Spirits? (Y/N) ");
        String input = scanner.next();

        if (input.toLowerCase().equals("n")) {
            System.out.println("\tThe Spirits wish you farewell... ");
            //TODO: aus dem Event raus, zurück zur map?
            return;
        }
        else if (input.toLowerCase().equals("y")) {
            System.out.print("\tChoose a Card: ");
            chosenCard = player.getDeck().get(scanner.nextInt());

            switch(chosenCard.getCardRarity()){
                case UNCOMMON:
                    System.out.println("\n\tThe flames grow slightly brighter.\n" +
                            "\tThe spirits continue dancing. You feel slightly warmer from their presence..The flame ");
                    player.increaseCurrentHealth(5);
                    System.out.println("\tYou gained extra health from this encounter. \n\tYour current hp are: " + player.getCurrentHealth());
                    break;
                case COMMON:
                    System.out.println("\n\tThe flames erupt, growing significantly stronger!\n" +
                            "\tThe spirits dance around you excitedly, filling you with a sense of warmth.");
                    player.setCurrentHealth(player.getMaxHealth());
                    System.out.println("\n\tYou gained extra health from this encounter. \n\tYour current hp are: " + player.getCurrentHealth());
                    break;
                case RARE:
                    System.out.println("\n\tThe flames burst, nearly knocking you off your feet, as the fire doubles in strength.\n" +
                            "\tThe spirits dance around you excitedly before merging into your form, filling you with warmth and strength.");
                    player.setCurrentHealth(player.getMaxHealth());
                    player.increaseMaxHealth(10);
                    System.out.println("\n\tYou gained extra health from this encounter. \nYour current hp are: " + player.getCurrentHealth());
                    break;
                default:
                    System.out.println("\n\tNothing happens... \n\n The spirits seem to be ignoring you now");
                    return;
            }
            player.getDeck().remove(chosenCard);
        }
        else {
            System.out.println("\n\tWrong input, try again ");
            startEvent();
        }
    }
}
