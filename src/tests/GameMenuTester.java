package tests;

import controller.menus.GameMenuViewController;
import models.game_settings.GameSettings;
import models.player.player_structure.Player;

public class GameMenuTester {
    public static void main(String[] args) {
        Player player = new TestPlayer();
        GameSettings.openGameMenu(player);
    }
}

