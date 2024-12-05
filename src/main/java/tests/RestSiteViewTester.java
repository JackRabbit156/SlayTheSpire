package tests;

import controller.gui.RestController;
import helper.GuiHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;

/**
 * @author Keil, Vladislav
 */
public class RestSiteViewTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer(primaryStage);

        GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);

        player.setCurrentHealth(666);

        GuiHelper.Scenes.startRestScene(player);
    }

}
