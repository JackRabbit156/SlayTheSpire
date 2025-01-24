package de.bundeswehr.auf.slaythespire.model.event.act_one;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Der Spieler erhält entweder Gold, oder verliert einen zufälligen Goldwert.
 * @author Keil, Vladislav
 * @author  Loeschner, Marijan
 */
public class WorldOfGoo extends Event {

    private final Button button1 = new Button("\t[Reach Inside] "); // get damage for 11 HP, get 75 gold
    private final Button button2 = new Button("\t[Leave It] "); // loose 20-50 gold

    public WorldOfGoo(Player player) {
        super(player, "World of Goo",
            "\n\nYou fall into a puddle.\n" +
                    "IT'S MADE OF SLIME GOO!!\n" +
                    "Frantically, you claw yourself out over several minutes as you feel the goop starting to burn.\n" +
                    "You can feel goop in your ears, goop in your nose, goop everywhere.\n");
        setImage(new PathAssistent().toPath(this));
    }

    @Override
    public Button getButton1() {
        button1.setOnAction(event -> {
            getPlayer().takeDamage(new GameContext(getPlayer(), new AttackContext(null, getPlayer(), 11, this)));
            getPlayer().gainGold(75);
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button1;
    }

    @Override
    public Button getButton2() {
        int lostGold = rnd.nextInt(50 + 1 - 20) + 20;
        button2.setOnAction(event -> {
            getPlayer().decreaseGold(lostGold);
            button2.setVisible(false);
            button1.setVisible(false);
        });
        return button2;
    }

}