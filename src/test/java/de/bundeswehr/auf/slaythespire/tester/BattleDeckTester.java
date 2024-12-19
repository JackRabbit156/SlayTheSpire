package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
/**
 * @author Keil, Vladislav
 */
public class BattleDeckTester {

    public static void main(String[] args) {
        Player player = TestPlayer.custom(null);

        BattleDeck battleDeck = new BattleDeck(player.getDeck());

        battleDeck.createShuffledDeck();
    }

}
