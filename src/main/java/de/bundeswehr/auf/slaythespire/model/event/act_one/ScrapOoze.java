package de.bundeswehr.auf.slaythespire.model.event.act_one;

import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Der Spieler kann 3 hp verlieren
 *
 * @author  Loeschner, Marijan
 */
public class ScrapOoze extends Event {

    public ScrapOoze(Player player) {
        super(player, "Scrap Ooze", new Image("/images/event/act_one/ScrapOozeEvent.png"),
            "\n\nAs you walk into the room you hear a gurgling and the grinding of metals. \n" +
                    "Before you is a slime-like creature that ate too much scrap for its own good. \n" +
                    "From the center of the creature you see glints of strange light, perhaps something magical? \n" +
                    "It looks like you can get some treasure if you just reach inside its... opening. \n" +
                    "However, the acid and sharp objects may hurt.\n"
        );
    }

    @Override
    public Button getButton1() {
        Button button1 = new Button("\t[Reach Inside] "); // hp -3
        button1.setOnAction(event -> {
            getPlayer().takeDamage(new GameContext(getPlayer(), new AttackContext(null, getPlayer(), 3, this)));
            button1.setVisible(false);
        });
        return button1;
    }
}
