package de.bundeswehr.auf.slaythespire.model.event.act_two;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
/**
 * Der Spieler kann Gold erhalten, oder verliert HP
 *
 * @author  Loeschner, Marijan
 */
public class TheNest extends Event {

    private final Button button1 = new Button("\t[Smash and Grab] "); // get 99 gold
    private final Button button2 = new Button("\t[Stay in Line]"); // decrease health by 6

    public TheNest(Player player) {
        super(player, "The Nest",
            "\n\nA long line of hooded figures can be seen entering an unassuming cathedral.\n" +
                    "Naturally, you join the line and are quickly surrounded by Cultists!\n" +
                    "They ignore you as they gleefully chant and wave their weapons around.\n" +
                    "You eye a Donation Box...\n");
        setImage(new PathAssistent().toPath(this));
    }


    @Override
    public Button getButton1() {
        button1.setOnAction(event -> {
            getPlayer().gainGold(99);
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button1;
    }

    @Override
    public Button getButton2() {
        button2.setOnAction(event -> {
            getPlayer().takeDamage(new GameContext(getPlayer(), new AttackContext(null, getPlayer(), 6, this)));
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button2;
    }
}
