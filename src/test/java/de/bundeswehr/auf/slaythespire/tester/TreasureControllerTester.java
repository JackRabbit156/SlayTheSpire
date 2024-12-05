package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.models.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.models.settings.structure.DifficultyLevel;

/**
 * @author Keil, Vladislav
 */
public class TreasureControllerTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer(primaryStage);

        GameSettings.setDifficultyLevel(DifficultyLevel.EASY);

        GuiHelper.Scenes.startTreasureScene(player);
    }

}
