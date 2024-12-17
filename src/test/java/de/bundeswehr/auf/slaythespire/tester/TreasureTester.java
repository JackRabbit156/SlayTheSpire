package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.potion.ExplosivePotion;
import javafx.application.Application;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;

/**
 * @author Keil, Vladislav
 */
public class TreasureTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer(primaryStage);

        GameSettings.setDifficultyLevel(DifficultyLevel.EASY);

        player.addPotionCard(new ExplosivePotion());
        player.addPotionCard(new ExplosivePotion());
        player.addPotionCard(new ExplosivePotion());

        GuiHelper.Scenes.startTreasureScene(player);
    }

}
