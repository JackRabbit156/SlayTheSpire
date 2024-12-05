package de.bundeswehr.auf.slaythespire.models.tests;

import de.bundeswehr.auf.slaythespire.models.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;
/**
 * @author Keil, Vladislav
 */
public class BattleDeckTester {

    public static void main(String[] args) {
        Player player = new TestPlayer(null);

        BattleDeck battleDeck = new BattleDeck(player.getDeck());

        battleDeck.createShuffledDeck();
    }

}
