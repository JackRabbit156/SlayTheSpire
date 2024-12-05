package tests;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.player.IroncladPlayer;
import models.potion.BloodPotion;
import models.potion.EnergyPotion;

public class EventTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        IroncladPlayer player = new IroncladPlayer(primaryStage);

        GuiHelper.Scenes.startEventScene(player);
    }

}
