package de.bundeswehr.auf.slaythespire.model.event.act_two;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
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
        super(player, "The Mausoleum",
                "\n\nVenturing through a series of tombs, you are faced with a large sarcophagus studded with gems \n" +
                        "in the center of a circular room.\n" +
                        "You cannot make out the writing on the coffin, however, you do notice black fog seeping out from the sides.\n");
        setImage(new PathAssistent().toPath(this));
    }

    @Override
    public Button getButton1() {
        Button button1 = new Button("\t[Open coffin] "); // decrease health by 50%
        button1.setOnAction(event -> getPlayer().takeDamage(
                new GameContext(getPlayer(), new AttackContext(null, getPlayer(), getPlayer().getCurrentHealth() / 2, this))));
        return button1;
    }

}

