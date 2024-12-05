package de.bundeswehr.auf.slaythespire.model.event.general;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.Random;

/**
 * Dupliziert eine zufällige Karte aus dem Deck des Spielers.
 *
 * @author Loeschner, Marijan
 */
public class NoteForYourself extends Event {
    private DeckFactory df;
    private Image image = new Image("/images/event/general/NoteForYourselfEvent.png");
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
