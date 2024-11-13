package tests;

import controller.cli.RestViewController;
import models.player.IroncladPlayer;

public class RestSiteViewTester {
    public static void main(String[] args) {
        IroncladPlayer player = new IroncladPlayer();

        RestViewController rest = new RestViewController(player);

        rest.startRest();
    }
}
