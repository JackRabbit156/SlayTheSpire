package tests;

import controller.gui.ShopController;
import helper.GuiHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.player.IroncladPlayer;

import java.util.Objects;

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
    public void start(Stage primaryStage) {
//        IroncladPlayer player = new IroncladPlayer();
        TestPlayer player = new TestPlayer();

//        player.setGold(500);
        GameSettings.setDifficultyLevel(DifficultyLevel.HARD);
        ShopController shopController = new ShopController(player);

        shopController.entryShop();

        Scene scene = new Scene(shopController.getShopView(), 1920, 1080);
        scene.getStylesheets().add(Objects.requireNonNull(GuiHelper.class.getResource("/css/mapStyle.css")).toExternalForm());

        player.setPrimaryStage(primaryStage);

        GuiHelper.Scenes.startScene(primaryStage, scene, "Slay the Spire - JavaFX");

//        primaryStage.setScene(scene);

//        primaryStage.setTitle("Slay the Spire - JavaFX");
//        primaryStage.show();
    }
}
