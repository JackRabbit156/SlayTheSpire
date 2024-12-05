package models.event.generelevents;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import models.card.DeckFactory;
import models.card.card_structure.Card;
import models.event.Event;
import models.player.player_structure.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Dupliziert eine zufällige Karte aus dem Deck des Spielers.
 *
 * @author Loeschner, Marijan
 */
public class NoteForYourself extends Event {
    private DeckFactory df;
    private Image image = new Image("/images/event/generalevents/NoteForYourselfEvent.png");
    private String title = "A Note for Yourself";
    private String story = "\n\nYou spot a loose brick within a pillar that catches your eye.\n" +
            "You find a folded note and a card inside. It reads \"The Heart awaits.\" \n" +
            "This is your handwriting.\n";
    private Button agree = new Button("\t[Take] ");
    private Random rand = new Random();

    public NoteForYourself() {
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
        agree.setOnMouseClicked(event -> {
            // random Integer um die Karte an der stelle der Deck-Liste auszuwählen und hinzuzufügen
            int cardInt = rand.nextInt(player.getDeck().size());
            player.addCardToDeck(player.getDeck().get(cardInt));
            agree.setVisible(false);
        });
        return agree;
    }
}
