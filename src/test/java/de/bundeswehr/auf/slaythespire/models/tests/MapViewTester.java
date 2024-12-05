package de.bundeswehr.auf.slaythespire.models.tests;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.models.game_settings.GameSettings;
import de.bundeswehr.auf.slaythespire.models.game_settings.structure.DifficultyLevel;
import de.bundeswehr.auf.slaythespire.models.potion.BloodPotion;
import de.bundeswehr.auf.slaythespire.models.potion.EnergyPotion;

/**
 * @author Keil, Vladislav
 */
public class MapViewTester  extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer(primaryStage);

        player.setCurrentHealth(123);

        player.setCurrentField("7");

        GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);

        player.setGold(500);

        player.getPotionCards().add(new BloodPotion());
        player.getPotionCards().add(new EnergyPotion());
        player.getPotionCards().add(new EnergyPotion());

        GuiHelper.Scenes.startMapScene(player);
    }

}
