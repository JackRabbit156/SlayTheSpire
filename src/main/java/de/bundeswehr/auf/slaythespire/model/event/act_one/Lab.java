package de.bundeswehr.auf.slaythespire.model.event.act_one;

import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.PotionFactory;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * Der Spieler kann Potions erhalten
 *
 * @author Loeschner, Marijan
 */
public class Lab extends Event {

    public Lab(Player player) {
        super(player, "Lab", new Image("/images/event/general/LabEvent.jpg"),
                "\n\nYou find yourself in a room filled with racks of \n" +
                        "test tubes, beakers, flasks, forceps, pinch clamps, stirring rods, tongs, \n" +
                        "goggles, funnels, pipettes, cylinders, condensers, and even a rare spiral tube of glass.\n" +
                        "Why do you know the name of all these tools? It doesn't matter, you take a look around.\n"
        );
    }

    @Override
    public Button getButton1() {
        Potion potion = PotionFactory.generatePotion();
        Button button1 = new Button("\t[Search] Obtain Potions"); // get a potion
        button1.setOnAction(event -> {
            if (getPlayer().getPotions().size() < 3) {
                getPlayer().addPotion(potion);
            }
            button1.setVisible(false);
        });
        return button1;
    }

}
