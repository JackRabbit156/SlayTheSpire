package tests;

import controller.gui.RestViewController;
import controller.gui.ShopViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.player.IroncladPlayer;

public class RestSiteViewTester extends Application {
    public static void main(String[] args) { launch(args); }
    @Override
    public void start(Stage primaryStage) throws Exception {
        IroncladPlayer player = new IroncladPlayer();
//        player.setGold(500);
        RestViewController shopViewController = new RestViewController(player);

        shopViewController.startRest();

        Scene scene = new Scene(shopViewController.getRestView(), 1920, 1080);

//        scene.getStylesheets().add(getClass().getResource("/debug.css").toExternalForm());
//        player.setPrimaryStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Slay the Spire - JavaFX");
        primaryStage.show();
    }
}
