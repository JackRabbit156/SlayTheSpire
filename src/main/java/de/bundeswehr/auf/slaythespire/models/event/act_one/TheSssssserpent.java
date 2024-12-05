package de.bundeswehr.auf.slaythespire.models.event.act_one;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.models.event.Event;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

/**
 * Der Spieler kann 175 Gold erhalten.
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class TheSssssserpent extends Event {
    private static Image image = new Image("/images/event/act_one/TheSssssserpentEvent.png");
    private String title = "TheSssssserpent";
    private String story = "\n\nYou walk into a room to find a large hole in the ground.\n" +
            "As you approach the hole, an enormous serpent creature appears from within.\n";
    private Button agree = new Button("\t[Agree] ");

    public TheSssssserpent() {
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
            player.increaseGold(175);
            agree.setVisible(false);

        });
        return agree;
    }
}