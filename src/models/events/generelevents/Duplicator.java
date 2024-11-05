package models.events.generelevents;

import models.cards.card_structure.Card;
import models.events.Event;
import models.player.player_structure.Player;
import view.EventView;

import java.util.List;
import java.util.Scanner;

/**
 * Spieler kann entscheiden, welche Karte er duplizieren möchte.
 * @author Keil, Vladislav
 */
public class Duplicator extends Event {
    private Player player;
    private Scanner scanner = new Scanner(System.in);

    public Duplicator(Player player) {
        super("Before you lies a decorated altar to some ancient entity.", "Duplicator");

        this.player = player;
        startEvent();
    }

    @Override
    public void startEvent() {
        List<Card> exchangeDeck = player.getDeck();
        Card exchangeCard;
        String input = scanner.next();
        EventView.displayStory(getTitle(), getStory());
        System.out.println("1. Pray \n2. Leave \n" + "\nChoose an option: ");
        if (input.equals("1")) {
            System.out.println("You kneel respectfully. A ghastly mirror image appears from the shrine and collides into you.");
            //Karten auflisten.
            EventView.viewDeck(player);
            System.out.print("Choose a Card to duplicate: ");
            //Ausgewählte Karte zum Deck hinzufügen
            exchangeCard =  exchangeDeck.get(scanner.nextInt());
            exchangeDeck.add(exchangeCard);
        }
        else if (input.equals("2")) {
            System.out.println("You ignore the shrine, confident in your choice.");
        }
        else {
            System.out.println("Wrong input, please try again");
            startEvent();
        }
    }
}

