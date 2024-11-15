package tests;

import controller.gui.ShopViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.cards.card_structure.Card;
import models.player.Ironclad;
import models.potion.potion_structure.PotionCard;
import models.relics.relic_structure.Relic;
import view.gui.layouts.layout_events.ShopViewEvents;

/**
 * @author Keil, Vladislav
 */
public class ShopControllerTester extends Application {
    public static void main(String[] args) { launch(args); }
//    public static void main(String[] args) {
//        /* Test 1 X-Karten kaufen */
//        player.setGold(150);
//
//
//        // DEBUG
//        System.out.println("Deck before entry Shop");
//        System.out.println("Erwarte 10 Karten: " + player.getDeck().size());
//
//        shopViewController.entryShop();
//
//        // DEBUG
//        System.out.println("Deck after entry Shop");
//        System.out.println("Erwarte 10 + X Karten: " + player.getDeck().size());
//
//        /* Test 2 Keine Karte kaufbar */
//        player.setGold(0);
//        shopViewController.entryShop();
//        System.out.println("Erwarte, dass keine Karte gekauft werden kann");
//        System.out.println("Erwarte, dass die selben Karten wie bei Letzten aufruf existieren.");
//
//
//        /* Test 3 Z-Karten kaufen */
//        shopViewController = new ShopViewController(player);
//        player.setGold(50);
//        shopViewController.entryShop();
//        System.out.println("Erwarte, dass 5 neue Karten existieren");
//        System.out.println("Erwarte 10 + X + Z Karten: " + player.getDeck().size());
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Ironclad player = new Ironclad();
//        player.setGold(500);
        ShopViewController shopViewController = new ShopViewController(player);

        shopViewController.entryShop();

        Scene scene = new Scene(shopViewController.getShopView(), 1920, 1080);

//        scene.getStylesheets().add(getClass().getResource("/debug.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Slay the Spire - JavaFX");
        primaryStage.show();
    }
}
