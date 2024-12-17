package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;

/**
 * @author Keil, Vladislav
 */
public class GameOverTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer(primaryStage);

        player.setCurrentAct(2);

        GameSettings.setStats(12_345, 999_999, 10_000_000, 1_234_567);

        GuiHelper.Scenes.startGameOverScene(player);
    }

}
