package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;

/**
 * @author Keil, Vladislav
 */
public class RestSiteTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = TestPlayer.cheater(primaryStage);

        GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);

        player.setCurrentHealth(666);

        GuiHelper.Scenes.startRestScene(player);
    }

}
