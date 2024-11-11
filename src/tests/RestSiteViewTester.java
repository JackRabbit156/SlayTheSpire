package tests;

import controller.cli.RestViewController;
import models.player.Ironclad;

public class RestSiteViewTester {
    public static void main(String[] args) {
        Ironclad player = new Ironclad();

        RestViewController rest = new RestViewController(player);

        rest.startRest();
    }
}
