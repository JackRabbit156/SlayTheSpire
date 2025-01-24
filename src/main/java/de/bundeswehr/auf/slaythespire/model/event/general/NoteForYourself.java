package de.bundeswehr.auf.slaythespire.model.event.general;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.util.List;

/**
 * Dupliziert eine zufällige Karte aus dem Deck des Spielers.
 *
 * @author Loeschner, Marijan
 */
public class NoteForYourself extends Event {

    public NoteForYourself(Player player) {
        super(player, "A Note for Yourself",
                "\n\nYou spot a loose brick within a pillar that catches your eye.\n" +
                        "You find a folded note and a card inside. It reads \"The Heart awaits.\" \n" +
                        "This is your handwriting.\n");
        setImage(new PathAssistent().toPath(this));
    }

    @Override
    public Button getButton1() {
        Button take = new Button("\t[Take] "); // duplicates a random card of your deck
        take.setOnAction(event -> {
            List<Card> deck = getPlayer().getDeck();
            // random Integer um die Karte an der stelle der Deck-Liste auszuwählen und hinzuzufügen
            int cardInt = rnd.nextInt(deck.size());
            getPlayer().addCardToDeck(deck.get(cardInt));
            take.setVisible(false);
        });
        return take;
    }

}
