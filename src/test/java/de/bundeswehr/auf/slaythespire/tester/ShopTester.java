package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;
import de.bundeswehr.auf.slaythespire.model.potion.BloodPotion;
import de.bundeswehr.auf.slaythespire.model.potion.EnergyPotion;

/**
 * @author Keil, Vladislav
 */
public class ShopTester extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer(primaryStage);

        player.setGold(500);

        GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);

        player.getPotions().add(new BloodPotion());
        player.getPotions().add(new EnergyPotion());
//        player.getPotions().add(new EnergyPotion());

        GuiHelper.Scenes.startShopScene(player);
    }
}
