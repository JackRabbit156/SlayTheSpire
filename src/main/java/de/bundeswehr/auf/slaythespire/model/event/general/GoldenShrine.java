package de.bundeswehr.auf.slaythespire.model.event.general;

import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * Erhöht Gold um 100
 *
 * @author Loeschner, Marijan
 */
public class GoldenShrine extends Event {


    public GoldenShrine(Player player) {
        super(player, "Golden Shrine", new Image("/images/event/general/GoldenShrineEvent.png"),
                "\n\nBefore you lies an elaborate shrine to an ancient spirit.\n");
    }

    @Override
    public Button getButton1() {
        Button button1 = new Button("\t[Pray] "); // get 100 gold
        button1.setOnAction(event -> {
            getPlayer().gainGold(100);
            button1.setVisible(false);
        });
        return button1;
    }

}
