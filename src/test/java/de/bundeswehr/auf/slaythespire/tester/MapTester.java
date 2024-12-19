package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;
import de.bundeswehr.auf.slaythespire.model.potion.common.BloodPotion;
import de.bundeswehr.auf.slaythespire.model.potion.common.EnergyPotion;

/**
 * @author Keil, Vladislav
 */
public class MapTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = TestPlayer.ironclad(primaryStage);

        player.setCurrentHealth(123);

        player.setCurrentField("7");

        GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);

        player.setGold(500);

        player.getPotions().add(new BloodPotion());
        player.getPotions().add(new EnergyPotion());
        player.getPotions().add(new EnergyPotion());

        GuiHelper.Scenes.startMapScene(player);
    }

}
