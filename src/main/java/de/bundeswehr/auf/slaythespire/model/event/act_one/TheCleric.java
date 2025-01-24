package de.bundeswehr.auf.slaythespire.model.event.act_one;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Der Spieler kann  im austausch gegen Gold 1. die MaxHP erhöhen, oder 2. Eine Karte aus dem Spielerdeck entfernen.
 * @author  Loeschner, Marijan
 */
public class TheCleric extends Event {

    private final Button button1 = new Button("\t[Heal] "); // Lose 35 Gold. Heal 25% of your Max HP.
    private final Button button2 = new Button("\t[Purify] "); // Lose 50 Gold. Remove a random Card from your deck.

    public TheCleric(Player player) {
        super(player, "The Cleric",
            "\n\nA strange blue humanoid with a golden helm(?) approaches you with a huge smile.\n" +
                    "\"Hello friend! I am Cleric! Are you interested in my services?!\"\n" +
                    "\"the creature shouts, loudly.\"\n");
        setImage(new PathAssistent().toPath(this));
    }

    @Override
    public Button getButton1() {
        button1.setOnAction(event -> {
            if (getPlayer().getGold() >= 35) {
                getPlayer().heal(getPlayer().getMaxHealth() / 4);
                getPlayer().decreaseGold(35);
            }
            else {
                LoggingAssistant.log("Not enough gold to heal", Color.YELLOW);
                getEventView().showDialog("You don't have enough gold.");
            }
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button1;
    }

    @Override
    public Button getButton2() {
        button2.setOnAction(event -> {
            if (getPlayer().getGold() >= 50) {
                DeckFactory deckFactory = new DeckFactory(getPlayer(), 1);
                deckFactory.removeRandomCard(getPlayer());
                getPlayer().decreaseGold(50);
            }
            else {
                LoggingAssistant.log("Not enough gold to remove card", Color.YELLOW);
                getEventView().showDialog("You don't have enough gold.");
            }
            button1.setVisible(false);
            button2.setVisible(false);
        });
        return button2;
    }

}
