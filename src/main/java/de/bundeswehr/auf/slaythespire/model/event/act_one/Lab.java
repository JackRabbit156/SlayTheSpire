package de.bundeswehr.auf.slaythespire.model.event.act_one;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
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
        super(player, "Lab",
                "\n\nYou find yourself in a room filled with racks of \n" +
                        "test tubes, beakers, flasks, forceps, pinch clamps, stirring rods, tongs, \n" +
                        "goggles, funnels, pipettes, cylinders, condensers, and even a rare spiral tube of glass.\n" +
                        "Why do you know the name of all these tools? It doesn't matter, you take a look around.\n");
        setImage(new PathAssistent().toPath(this));
    }

    @Override
    public Button getButton1() {
        Button button1 = new Button("\t[Search] "); // Obtain 3 random Potions.
        button1.setOnAction(event -> {
            if (getPlayer().getPotions().size() < 3) {
                getPlayer().addPotion(PotionFactory.generatePotion());
            }
            button1.setVisible(false);
        });
        return button1;
    }

}
