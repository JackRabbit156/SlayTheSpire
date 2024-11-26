package tests;

import controller.gui.ShopController;
import helper.GuiHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.player.IroncladPlayer;
import models.potion.BloodPotion;
import models.potion.EnergyPotion;

import java.util.Objects;

/**
 * @author Keil, Vladislav
 */
public class ShopControllerTester extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
//        IroncladPlayer player = new IroncladPlayer();
        TestPlayer player = new TestPlayer(primaryStage);
//        player.setGold(500);
        GameSettings.setDifficultyLevel(DifficultyLevel.HARD);
        BloodPotion bloodPotion = new BloodPotion();
        EnergyPotion energyPotion = new EnergyPotion();
        EnergyPotion energyPotion1 = new EnergyPotion();
//
        player.getPotionCards().add(bloodPotion);
        player.getPotionCards().add(energyPotion);
        player.getPotionCards().add(energyPotion1);
        GuiHelper.Scenes.startShopScene(player);
    }
}
