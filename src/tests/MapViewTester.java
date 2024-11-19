package tests;

import controller.cli.MapViewController;
import controller.gui.MapController;
import helper.GuiHelper;
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
        player.setPrimaryStage(primaryStage);
        GuiHelper.Scenes.startScene(primaryStage, scene, "Slay the Spire - JavaFX");

    }
}
