package de.bundeswehr.auf.slaythespire.model.event.act_two;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.curse.DecayCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.RelicFactory;
import de.bundeswehr.auf.slaythespire.model.relic.event.BloodyIdolRelic;
import de.bundeswehr.auf.slaythespire.model.relic.event.GoldenIdolRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * You encounter a forgotten statue that requires an offering.
 *
 * @author L Frank Rieger
 */
public class ForgottenAltar extends Event {

    /**
     * Choosing this option while already having Bloody Idol does not remove Golden Idol,
     * allowing you to finish the event with no effect.
     */
    private final Button button1 = new Button("\t[Offer: Golden Idol] "); // Obtain Bloody Idol. Lose Golden Idol.
    private final Button button2 = new Button("\t[Sacrifice] "); // Gain 5 Max HP. Lose 25% of Max HP.
    private final Button button3 = new Button("\t[Desecrate] "); // Become Cursed - Decay.

    public ForgottenAltar(Player player){
        super(player, "Forgotten Altar",
            "\n\nIn front of you sits an altar to a forgotten god.\n" +
                    "Atop the altar sits an ornate female statue with arms outstretched.\n" +
                    "She calls out to you, demanding sacrifice.\n");
        setImage(new PathAssistent().toPath(this));
        // TODO Leave Button nicht anzeigen
    }

    @Override
    public Button getButton1() {
        boolean hasGoldenIdol = false;
        AtomicBoolean hasBloodyIdol = new AtomicBoolean(false);
        for (Relic relic : getPlayer().getRelics()) {
            if (relic instanceof GoldenIdolRelic) {
                hasGoldenIdol = true;
            }
            else if (relic instanceof BloodyIdolRelic) {
                hasBloodyIdol.set(true);
            }
        }
        if (hasGoldenIdol) {
            button1.setOnAction(event -> {
                if (!hasBloodyIdol.get()) {
                    getPlayer().removeRelic(GoldenIdolRelic.class);
                    getPlayer().addRelic(new BloodyIdolRelic());
                    RelicFactory.remove(BloodyIdolRelic.class);
                }
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
            });
            return button1;
        }
        return null;
    }

    @Override
    public Button getButton2() {
        button2.setOnAction(event -> {
            getPlayer().gainMaxHp(5);
            getPlayer().looseMaxHp((int) (getPlayer().getMaxHealth() * 0.25));
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);
        });
        return button2;
    }

    @Override
    public Button getButton3() {
        button3.setOnAction(event -> {
            getPlayer().addCardToDeck(new DecayCard());
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);
        });
        return button3;
    }

}
