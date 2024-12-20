package de.bundeswehr.auf.slaythespire.helper;

import de.bundeswehr.auf.slaythespire.model.player.TestPlayer;
import javafx.application.Application;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;

/**
 * @author Keil, Vladislav
 */
public class StatisticsTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = TestPlayer.cheater(primaryStage);

        player.setCurrentAct(2);

        GameSettings.setStats(12_345, 999_999, 10_000_000, 1_234_567);

        GuiHelper.Scenes.startStatisticScene(player);
    }

}
