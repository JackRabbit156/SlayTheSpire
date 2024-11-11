package tests;

import controller.cli.MapViewController;
import models.player.player_structure.Player;

public class MapViewTester {
    public static void main(String[] args) {
        Player player = new TestPlayer();

        MapViewController map = new MapViewController(player, false);

    }
}
