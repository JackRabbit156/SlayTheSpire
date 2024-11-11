package tests;

import controller.cli.TreasureViewController;
import models.player.player_structure.Player;

public class TreasureTester {
    public static void main(String[] args) {
        Player player = new TestPlayer();
        TreasureViewController treasure = new TreasureViewController(player);
    }
}
