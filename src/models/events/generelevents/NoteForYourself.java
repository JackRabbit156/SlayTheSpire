package models.events.generelevents;

import models.cards.card_structure.Card;
import models.events.Event;
import models.player.player_structure.Player;
import view.EventView;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Dupliziert eine zufällige Karte aus dem Deck des Spielers.
 * @author Keil, Vladislav
 * @author Loeschner, Marijan
 */
public class NoteForYourself extends Event {
    Player player;
    Random randi = new Random();
    Scanner scanner = new Scanner(System.in);

    public NoteForYourself(Player player) {
        super("You spot a loose brick within a pillar that catches your eye.\n" +
                "\tYou find a folded note and a card inside. It reads \"The Heart awaits.\" This is your handwriting.", "Note For Yourself");
        this.player = player;
        startEvent();
    }

    @Override
    public void startEvent() {
        List<Card> playerDeck = player.getDeck();
        Card selectedCard = chooseExistingCard(playerDeck);

        EventView.displayStory(getTitle(), getStory());

        playerDeck.add(selectedCard);

        System.out.println();
        System.out.printf("%s %s %n", "\tYou recieve:", selectedCard.getName());
        System.out.println();
        System.out.println("\tPress (0) for exit");
        scanner.nextInt();
        return;
    }

    private Card chooseExistingCard(List<Card> playerDeck) {
        return playerDeck.get(randi.nextInt(playerDeck.size()));
    }
}
