package tests;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import models.game_settings.GameSettings;
import models.game_settings.structure.GameMode;
import models.player.IroncladPlayer;

/**
 * @author Keil, Vladislav
 */
public class MapViewTester  extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer(primaryStage);
//        IroncladPlayer player = new IroncladPlayer();
        player.setCurrentField("15");
        GuiHelper.Scenes.startMapScene(player);
    }
}
