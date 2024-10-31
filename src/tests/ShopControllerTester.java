package tests;

import controller.ShopViewController;
import models.player.Ironclad;


public class ShopControllerTester {
    public static void main(String[] args) {
        Ironclad player = new Ironclad();

        ShopViewController shopViewController = new ShopViewController(player);
        /* Test 1 X-Karten kaufen */
        player.setGold(150);

        // DEBUG
        System.out.println("Deck before entry Shop");
        System.out.println("Erwarte 10 Karten: " + player.getDeck().size());

        shopViewController.entryShop();

        // DEBUG
        System.out.println("Deck after entry Shop");
        System.out.println("Erwarte 10 + X Karten: " + player.getDeck().size());

        /* Test 2 Keine Karte kaufbar */
        player.setGold(0);
        shopViewController.entryShop();
        System.out.println("Erwarte, dass keine Karte gekauft werden kann");
        System.out.println("Erwarte, dass die selben Karten wie bei Letzten aufruf existieren.");


        /* Test 3 Z-Karten kaufen */
        shopViewController = new ShopViewController(player);
        player.setGold(50);
        shopViewController.entryShop();
        System.out.println("Erwarte, dass 5 neue Karten existieren");
        System.out.println("Erwarte 10 + X + Z Karten: " + player.getDeck().size());
    }
}
