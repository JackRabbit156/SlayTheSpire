package models.event.act_one;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import models.event.Event;
import models.player.player_structure.Player;

import java.util.Scanner;
/**
 * Der Spieler kann 3 hp verlieren
 *
 * @author  Loeschner, Marijan
 */
public class ScrapOoze extends Event {
    private static Image image = new Image("/images/event/act_one/ScrapOozeEvent.png");
    private String title = "Scrap Ooze";
    private String story = "\n\nAs you walk into the room you hear a gurgling and the grinding of metals. \n" +
            "Before you is a slime-like creature that ate too much scrap for its own good. \n" +
            "From the center of the creature you see glints of strange light, perhaps something magical? \n" +
            "It looks like you can get some treasure if you just reach inside its... opening. \n" +
            "However, the acid and sharp objects may hurt.\n";
    private Button button = new Button("\t[Reach Inside] ");

    public ScrapOoze() {
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

        button.setOnMouseClicked(event -> {
            player.increaseCurrentHealth(-3);
            button.setVisible(false);
        });
        return button;
    }
}
