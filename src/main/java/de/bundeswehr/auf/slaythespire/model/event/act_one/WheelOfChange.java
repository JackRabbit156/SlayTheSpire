package de.bundeswehr.auf.slaythespire.model.event.act_one;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * Der Spieler kann einen Preis gewinnen
 *
 * @author Loeschner, Marijan
 */
public class WheelOfChange extends Event {

    public WheelOfChange(Player player) {
        super(player, "Wheel of Change",
                "\n\nYou come upon a dapper looking, cheery gremlin.\n" +
                        "Gremlin: \"It's time to spin the wheel! Are you R E A D Y ? Of course you are!\"\n");
        setImage(new PathAssistent().toPath(this));
    }

    @Override
    public Button getButton1() {
        Button button1 = new Button("\t[Play] Spin the Wheel! ");
        button1.setOnAction(event -> {
            DeckFactory factory = new DeckFactory(getPlayer(), 1);
            button1.setVisible(false);
            int randInt = rnd.nextInt(4);
            switch (randInt) {
                case 1: // get 100/ 200/ 300 gold
                    getPlayer().gainGold(getPlayer().getCurrentAct() * 100);
                case 2: // remove 1 card
                    factory.removeRandomCard(getPlayer());
                case 3: // get damaged by 10%
                    getPlayer().setCurrentHealth((int) (getPlayer().getCurrentHealth() * 0.9));
                default: // heal 100%
                    getPlayer().heal(getPlayer().getMaxHealth());
            }
        });
        return button1;
    }

}
