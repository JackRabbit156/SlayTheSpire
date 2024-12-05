package de.bundeswehr.auf.slaythespire.models.event.generelevents;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.models.event.Event;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

/**
 * Erhöht Gold um 100
 *
 * @author Loeschner, Marijan
 */
public class GoldenShrine extends Event {
    private Image image = new Image("/images/event/generalevents/GoldenShrineEvent.png");
    private String title = "Golden Shrine";
    private String story = "\n\nBefore you lies an elaborate shrine to an ancient spirit.\n";
    private Button button1 = new Button("\t[Pray] ");

    public GoldenShrine() {
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
            player.increaseGold(100);
            button1.setVisible(false);
        });
        return button1;
    }
}
