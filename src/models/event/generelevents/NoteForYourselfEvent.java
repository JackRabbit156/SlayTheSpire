package models.event.generelevents;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.card.card_structure.Card;
import models.event.Event;
import models.player.player_structure.Player;
import view.cli.EventView;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Dupliziert eine zufällige Karte aus dem Deck des Spielers.
 * @author Keil, Vladislav
 * @author Loeschner, Marijan
 */
public class NoteForYourselfEvent extends Event {
    private Player player;
    private Random randi = new Random();
    private Scanner scanner = new Scanner(System.in);

    public NoteForYourselfEvent(Player player) {
        super("  You spot a loose brick within a pillar that catches your eye.\n" +
                "\tYou find a folded note and a card inside. It reads \"The Heart awaits.\" \n" +
                "\tThis is your handwriting.\n", "Note For Yourself");
        setImagePath(new PathAssistent().toPath(this));
        this.player = player;
    }

    @Override
    public void startEvent() {
        ConsoleAssistent.clearScreen();
        List<Card> playerDeck = player.getDeck();
        Card selectedCard = chooseExistingCard(playerDeck);

        EventView.displayHead(getTitle(), getStory());

        playerDeck.add(selectedCard);

        System.out.println();
        System.out.printf("%s %s %n", "\tYou recieve:", selectedCard.getName());
        System.out.println();
        System.out.print("\tPress (0) for exit ");
        scanner.next();
        return;
    }

    private Card chooseExistingCard(List<Card> playerDeck) {
        return playerDeck.get(randi.nextInt(playerDeck.size()));
    }
}
