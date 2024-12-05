package de.bundeswehr.auf.slaythespire.model.event.act_two;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
/**
 * Spieler kann entscheiden, welche Karte er duplizieren möchte.
 *
 * @author Loeschner, Marijan
 */
public class TheMausoleum extends Event{

        private TilePane box = new TilePane();
        private Popup cardPopup = new Popup();
        private Image image = new Image("/images/event/act_two/mausoleum.jpg");
        private String title = "The Mausoleum";
        private String story = "\n\nVenturing through a series of tombs, you are faced with a large sarcophagus studded with gems \n" +
                "in the center of a circular room.\n" +
                "You cannot make out the writing on the coffin, however, you do notice black fog seeping out from the sides.\n";
        private Button button1 = new Button("\t[Open coffin] ");

        public TheMausoleum() {
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
                player.decreaseCurrentHealth(player.getCurrentHealth() / 2, false);
            });
            return button1;
        }
    }

