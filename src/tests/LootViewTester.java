package tests;

import controller.cli.LootViewController;
import controller.gui.LootController;
import controller.gui.ShopController;
import helper.GuiHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.map_elements.field_types.FieldEnum;
import view.gui.LootView;

import java.util.Objects;

public class LootViewTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer();
        GameSettings.setDifficultyLevel(DifficultyLevel.EASY);
        player.setPrimaryStage(primaryStage);
        //GuiHelper.Scenes.startLootScene(player, FieldEnum.ENEMYFIELD);
    }
}
