package de.bundeswehr.auf.slaythespire.helper;

import de.bundeswehr.auf.slaythespire.model.player.TestPlayer;
import de.bundeswehr.auf.slaythespire.model.potion.common.ExplosivePotion;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Easy;
import de.bundeswehr.auf.slaythespire.model.settings.structure.SuperEasy;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Keil, Vladislav
 */
public class TreasureTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = TestPlayer.silent(primaryStage);

        GameSettings.setDifficultyLevel(new SuperEasy());

        player.addPotion(new ExplosivePotion());
        player.addPotion(new ExplosivePotion());
//        player.addPotion(new ExplosivePotion());

        GuiHelper.Scenes.startTreasureScene(player);
    }

}
