package de.bundeswehr.auf.slaythespire.model.event.act_one;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Der Spieler kann 175 Gold erhalten.
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class TheSssssserpent extends Event {

    public TheSssssserpent(Player player) {
        super(player, "TheSssssserpent", new Image("/images/event/act_one/TheSssssserpentEvent.png"),
            "\n\nYou walk into a room to find a large hole in the ground.\n" +
                "As you approach the hole, an enormous serpent creature appears from within.\n");
    }

    @Override
    public Button getButton1() {
        Button agree = new Button("\t[Agree] "); // get 175 gold
        agree.setOnAction(event -> {
            getPlayer().gainGold(175);
            agree.setVisible(false);
        });
        return agree;
    }

}