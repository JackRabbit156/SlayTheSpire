package tests;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.game_settings.structure.GameMode;
import models.player.IroncladPlayer;
import models.potion.BloodPotion;
import models.potion.EnergyPotion;

/**
 * @author Keil, Vladislav
 */
public class MapViewTester  extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer(primaryStage);
//        IroncladPlayer player = new IroncladPlayer();
        player.setCurrentField("7");
//        player.setCurrentField("7");
        GameSettings.setDifficultyLevel(DifficultyLevel.HARD);

        BloodPotion bloodPotion = new BloodPotion();
        EnergyPotion energyPotion = new EnergyPotion();
        EnergyPotion energyPotion1 = new EnergyPotion();
//
        player.getPotionCards().add(bloodPotion);
        player.getPotionCards().add(energyPotion);
        player.getPotionCards().add(energyPotion1);

        GuiHelper.Scenes.startMapScene(player);
    }
}
