package tests;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.map_elements.field_types.FieldEnum;

public class TreasureControllerTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer();
        GameSettings.setDifficultyLevel(DifficultyLevel.EASY);
        player.setPrimaryStage(primaryStage);
        GuiHelper.Scenes.startTreasureScene(player);
    }
}
