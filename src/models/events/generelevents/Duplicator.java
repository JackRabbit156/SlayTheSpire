package models.events.generelevents;

import helper.ConsoleAssistent;
import models.cards.card_structure.Card;
import models.events.Event;
import models.player.player_structure.Player;
import view.cli.EventView;

import java.util.List;
import java.util.Scanner;

/**
 * Spieler kann entscheiden, welche Karte er duplizieren möchte.
 * @author Keil, Vladislav
 * @author Loeschner, Marijan
 */
public class Duplicator extends Event {
    private Player player;
    private Scanner scanner = new Scanner(System.in);

    public Duplicator(Player player) {
        super("  Before you lies a decorated altar to some ancient entity.", "Duplicator");

        this.player = player;
    }

    @Override
    public void startEvent() {
        ConsoleAssistent.clearScreen();
        List<Card> exchangeDeck = player.getDeck();
        Card exchangeCard;
        EventView.displayHead(getTitle(), getStory());
        System.out.println("\t1. Pray \n\t2. Leave \n");
        System.out.print("\n\tChoose an option: ");
        String input = scanner.next();
        if (input.equals("1")) {
            System.out.println("\n\tYou kneel respectfully. A ghastly mirror image appears from the shrine and collides into you.");
            //Karten auflisten.
            EventView.viewDeck(player);
            System.out.print("\n\tChoose a Card to duplicate: ");
            //Ausgewählte Karte zum Deck hinzufügen
            exchangeCard =  exchangeDeck.get(scanner.nextInt());
            exchangeDeck.add(exchangeCard);
        }
        else if (input.equals("2")) {
            System.out.println("\n\tYou ignore the shrine, confident in your choice.");
        }
        else {
            System.out.println("\n\tWrong input, please try again");
            startEvent();
        }
    }
}

