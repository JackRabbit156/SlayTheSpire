package de.bundeswehr.auf.slaythespire.model.event.act_two;

import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * Spieler kann entscheiden, welche Karte er duplizieren möchte.
 *
 * @author Loeschner, Marijan
 */
public class TheMausoleum extends Event {

    public TheMausoleum(Player player) {
        super(player, "The Mausoleum", new Image("/images/event/act_two/mausoleum.jpg"),
                "\n\nVenturing through a series of tombs, you are faced with a large sarcophagus studded with gems \n" +
                        "in the center of a circular room.\n" +
                        "You cannot make out the writing on the coffin, however, you do notice black fog seeping out from the sides.\n");
    }

    @Override
    public Button getButton1() {
        Button button1 = new Button("\t[Open coffin] "); // decrease health by 50%
        button1.setOnAction(event -> {
            getPlayer().decreaseCurrentHealth(getPlayer().getCurrentHealth() / 2, false);
        });
        return button1;
    }

}

