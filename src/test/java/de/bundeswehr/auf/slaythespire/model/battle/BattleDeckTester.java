package de.bundeswehr.auf.slaythespire.model.battle;

import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.TestPlayer;

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
