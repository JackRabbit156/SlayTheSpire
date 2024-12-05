package tests;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.map_elements.field_types.FieldEnum;
import models.potion.BloodPotion;
import models.potion.EnergyPotion;

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
