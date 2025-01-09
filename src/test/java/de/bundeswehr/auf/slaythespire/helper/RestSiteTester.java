package de.bundeswehr.auf.slaythespire.helper;

import de.bundeswehr.auf.slaythespire.model.player.TestPlayer;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Normal;
import javafx.application.Application;
import javafx.stage.Stage;

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

        GameSettings.setDifficultyLevel(new Normal());

        player.setCurrentHealth(666);

        GuiHelper.Scenes.startRestScene(player);
    }

}
