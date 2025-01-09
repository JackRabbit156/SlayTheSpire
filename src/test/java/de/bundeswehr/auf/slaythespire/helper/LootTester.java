package de.bundeswehr.auf.slaythespire.helper;

import de.bundeswehr.auf.slaythespire.model.map.field.FieldEnum;
import de.bundeswehr.auf.slaythespire.model.player.TestPlayer;
import de.bundeswehr.auf.slaythespire.model.potion.common.BloodPotion;
import de.bundeswehr.auf.slaythespire.model.potion.common.EnergyPotion;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Normal;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Keil, Vladislav
 */
public class LootTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = TestPlayer.silent(primaryStage);

        GameSettings.setDifficultyLevel(new Normal());

        player.getPotions().add(new BloodPotion());
        player.getPotions().add(new EnergyPotion());
//        player.getPotions().add(new EnergyPotion());

        GuiHelper.Scenes.startLootScene(player, FieldEnum.ENEMY_FIELD);
    }

}
