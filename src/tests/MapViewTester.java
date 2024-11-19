package tests;

import controller.cli.MapViewController;
import controller.gui.MapController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.player.player_structure.Player;

public class MapViewTester  extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer();
        MapController map = new MapController(player, true);

        Scene scene = new Scene(map.getMapView(), 1920, 1080);

//        scene.getStylesheets().add(getClass().getResource("/debug.css").toExternalForm());
        player.setPrimaryStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Slay the Spire - JavaFX");
        primaryStage.show();
    }
}
