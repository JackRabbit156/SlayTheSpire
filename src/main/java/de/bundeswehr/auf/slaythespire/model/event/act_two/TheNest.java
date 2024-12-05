package de.bundeswehr.auf.slaythespire.model.event.act_two;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
/**
 * Der Spieler kann Gold erhalten, oder verliert HP
 *
 * @author  Loeschner, Marijan
 */
public class TheNest extends Event {
    DeckFactory df;
    private static Image image = new Image("/images/event/act_two/theNest.jpg");
    private String title = "The Nest";
    private String story = "\n\nA long line of hooded figures can be seen entering an unassuming cathedral.\n" +
            "Naturally, you join the line and are quickly surrounded by Cultists!\n" +
            "They ignore you as they gleefully chant and wave their weapons around.\n" +
            "You eye a Donation Box...\n";
    private Button button1 = new Button("\t[Smash and Grab] Obtain potions");
    private Button button2 = new Button("\t[Stay in Line]");

    public TheNest() {
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
            player.increaseGold(99);
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button1;
    }

    public Button getButton2(Player player) {

        button2.setOnMouseClicked(event -> {
            player.decreaseCurrentHealth(6, false);
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button2;
    }
}
