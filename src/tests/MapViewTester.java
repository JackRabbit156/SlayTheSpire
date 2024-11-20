package tests;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;

public class MapViewTester  extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer();
        player.setPrimaryStage(primaryStage);
        GuiHelper.Scenes.startMapScene(player, true);
    }
}
