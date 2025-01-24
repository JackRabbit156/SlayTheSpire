package de.bundeswehr.auf.slaythespire.model.event.act_two;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.curse.DecayCard;
import de.bundeswehr.auf.slaythespire.model.card.skill.JAXCard;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.RelicFactory;
import de.bundeswehr.auf.slaythespire.model.relic.event.BloodyIdolRelic;
import de.bundeswehr.auf.slaythespire.model.relic.event.GoldenIdolRelic;
import de.bundeswehr.auf.slaythespire.model.relic.event.MutagenicStrengthRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import javafx.scene.control.Button;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * You encounter a shady man who wants to do an experiment on you, for science.
 *
 * @author L Frank Rieger
 */
public class Augmenter extends Event {

    private final Button button1 = new Button("\t[Test J.A.X] "); // Obtain a J.A.X.
    private final Button button2 = new Button("\t[Become Test Subject] "); // Choose and Transform 2 cards in your deck.
    private final Button button3 = new Button("\t[Ingest Mutagens] "); // Obtain a Mutagenic Strength.

    public Augmenter(Player player){
        super(player, "Augmenter",
            "\n\nA man with an eyepatch and a devilish grin strides up to you.\n" +
                    "Shady Man: \"Hey there, stranger. Interested in advancing science?\n" +
                    "I can make you stronger than any training or blessing.\n" +
                    "You're gonna need it if you're one of those heroes with a death wish.\"\n" +
                    "Shady Man: \"Whad'ya say?\"\n");
        setImage(new PathAssistent().toPath(this));
        // TODO Leave Button nicht anzeigen
    }

    @Override
    public Button getButton1() {
        button1.setOnAction(event -> {
            getPlayer().addCardToDeck(new JAXCard());
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);
        });
        return button1;
    }

    @Override
    public Button getButton2() {
        // TODO transform
//        button2.setOnAction(event -> {
//
//            button1.setVisible(false);
//            button2.setVisible(false);
//            button3.setVisible(false);
//        });
//        return button2;
        return null;
    }

    @Override
    public Button getButton3() {
        button3.setOnAction(event -> {
            getPlayer().addRelic(new MutagenicStrengthRelic());
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);
        });
        return button3;
    }

}
