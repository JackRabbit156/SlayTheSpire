package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.player.IroncladPlayer;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;
/**
 * @author Keil, Vladislav
 */
public class DeckFactoryTester {

    public static void main(String[] args) {
        Player player = TestPlayer.ironclad(null);
        for (int i = 0; i < 100; i++) {
            DeckFactory deckFactory = new DeckFactory(player, 5);
            List<Card> cards = deckFactory.init(true);
        }
    }

}
