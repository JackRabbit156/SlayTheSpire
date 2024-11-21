package tests;

import models.game_settings.GameSettings;
import models.player.player_structure.Player;
/**
 * @author Keil, Vladislav
 */
public class GameMenuTester {
    public static void main(String[] args) {
        Player player = new TestPlayer();
        GameSettings.openGameMenu(player);
    }
}

