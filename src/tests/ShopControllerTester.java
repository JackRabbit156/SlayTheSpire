package tests;

import controller.ShopViewController;
import models.player.Ironclad;


public class ShopControllerTester {
    public static void main(String[] args) {
        Ironclad player = new Ironclad();

        ShopViewController shopViewController = new ShopViewController(player);
        /* Test 1 */
        player.setGold(150);

        // DEBUG
        System.out.println("Deck before entry Shop");
        System.out.println("Erwarte 10 Karten: " + player.getDeck().size());

        shopViewController.entryShop();

        // DEBUG
        System.out.println("Deck after entry Shop");
        System.out.println("Erwarte 11 Karten: " + player.getDeck().size());

        /* Test 2 */
        player.setGold(0);
        shopViewController.entryShop();
        System.out.println("Erwarte, dass keine Karte gekauft werden kann");

    }
}
