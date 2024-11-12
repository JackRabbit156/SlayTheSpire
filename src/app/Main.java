package app;

import controller.cli.menus.MainMenuViewController;
import controller.gui.MapController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.enemy.Enemy;
import models.enemy.act_one.Cultist;
import models.player.Ironclad;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Ironclad player = new Ironclad();
        player.setPrimaryStage(primaryStage);

        MapController mapController = new MapController(player, true);

        // Scene Setup
        Scene scene = new Scene(mapController.getMapView(), 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("/css/mapStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Slay the Spire - JavaFX");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}