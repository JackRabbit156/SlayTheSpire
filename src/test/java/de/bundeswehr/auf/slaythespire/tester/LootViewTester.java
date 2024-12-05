package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.models.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.models.settings.structure.DifficultyLevel;
import de.bundeswehr.auf.slaythespire.models.map.field.FieldEnum;
import de.bundeswehr.auf.slaythespire.models.potion.BloodPotion;
import de.bundeswehr.auf.slaythespire.models.potion.EnergyPotion;

/**
 * @author Keil, Vladislav
 */
public class LootViewTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer(primaryStage);

        GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);

        player.getPotionCards().add(new BloodPotion());
        player.getPotionCards().add(new EnergyPotion());
//        player.getPotionCards().add(new EnergyPotion());

        GuiHelper.Scenes.startLootScene(player, FieldEnum.ENEMYFIELD);
    }

}
