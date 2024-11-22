package tests;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import models.player.IroncladPlayer;

/**
 * @author Keil, Vladislav
 */
public class MapViewTester  extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer();
//        IroncladPlayer player = new IroncladPlayer();
        player.setCurrentAct(2);
        player.setCurrentField("16");
        player.setPrimaryStage(primaryStage);
        GuiHelper.Scenes.startMapScene(player, true);
    }
}
