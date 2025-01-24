package de.bundeswehr.auf.slaythespire.model.event.general;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.RelicFactory;
import de.bundeswehr.auf.slaythespire.model.relic.event.CultistHeadpieceRelic;
import de.bundeswehr.auf.slaythespire.model.relic.event.FaceOfClericRelic;
import de.bundeswehr.auf.slaythespire.model.relic.event.GremlinVisageRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import javafx.scene.control.Button;

import java.util.Random;

/**
 * You found a mysterious man, who is very interested in your face.
 *
 * @author L Frank Rieger
 */
public class FaceTrader extends Event {

    private static final Random rnd = new Random();

    private final Button button1 = new Button("\t[Touch] "); // Lose HP equal to 10% of Max HP. Gain 75 Gold.
    private final Button button2 = new Button("\t[Trade] "); // Receive one event relic.

    public FaceTrader(Player player) {
        super(player, "Face Trader",
                "\n\nYou walk by an eerie statue holding several masks...\n" +
                        "Something behind you softly whispers, \"Stop.\"\n" +
                        "You swerve around to face the statue which is now facing you!\n" +
                        "On closer inspection, it's not a statue but a statuesque, gaunt man. Is he even breathing?\n" +
                        "Eerie Man: \"Face. Let me touch? Maybe trade?\"\n");
        setImage(new PathAssistent().toPath(this));
    }

    @Override
    public Button getButton1() {
        button1.setOnAction(event -> {
            int damage = (int) (getPlayer().getMaxHealth() * 0.1);
            GameContext gameContext = new GameContext(getPlayer(),
                    new AttackContext(null, getPlayer(), damage, this));
            getPlayer().takeDamage(gameContext);
            getPlayer().gainGold(75);
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button1;
    }

    @Override
    public Button getButton2() {
        button2.setOnAction(event -> {
            Relic relic;
            do {
                switch (rnd.nextInt(3)) {
                    case 0:
                        relic = new CultistHeadpieceRelic();
                        break;
                    case 1:
                        relic = new FaceOfClericRelic();
                        break;
                    case 2:
                    default:
                        relic = new GremlinVisageRelic();
                        break;
                }
                if (!RelicFactory.remove(relic.getClass())) {
                    relic = null;
                }
            } while (relic == null);
            getPlayer().addRelic(relic);
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button2;
    }

}
