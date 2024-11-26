package models.event.generelevents;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import models.card.card_structure.Card;
import models.event.Event;
import models.player.player_structure.Player;

import java.util.List;
import java.util.Scanner;

/**
 * Spieler kann entscheiden, welche Karte er duplizieren möchte.
 *
 * @author Loeschner, Marijan
 */
public class DuplicatorEvent extends Event {
    private Image image = new Image("/images/event/generalevents/DuplicatorEvent.png");
    private String title = "Duplicator";
    private String story = "\n\nBefore you lies a decorated altar to some ancient entity.\n";
    private Button button1 = new Button("\t[Pray] ");
    private Card chosenCard;

    public DuplicatorEvent() {
        super();
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getStory() {
        return story;
    }

    public Image getImage() {
        return image;
    }

    public Button getButton1(Player player) {

        button1.setOnMouseClicked(event -> {
            //TODO: Karte wählen die dupliziert wird

            button1.setVisible(false);
        });
        return button1;
    }
  /*  private Player player;
    private Scanner scanner = new Scanner(System.in);

    public DuplicatorEvent(Player player) {
        super("  Before you lies a decorated altar to some ancient entity.", "Duplicator");
        setImagePath(new PathAssistent().toPath(this));

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
    }*/
}

