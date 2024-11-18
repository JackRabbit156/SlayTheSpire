package app;

import controller.gui.MapController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.player.Ironclad;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Ironclad player = new Ironclad();
        player.setPrimaryStage(primaryStage);

        MapController mapController = new MapController(player, true);

        // Scene Setup
        Scene scene = new Scene(mapController.getMapView(), 1920, 1080);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Slay the Spire - JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}