package tests;

import controller.gui.RestController;
import helper.GuiHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * @author Keil, Vladislav
 */
public class RestSiteViewTester extends Application {
    public static void main(String[] args) { launch(args); }
    @Override
    public void start(Stage primaryStage) throws Exception {
//        IroncladPlayer player = new IroncladPlayer();
        TestPlayer player = new TestPlayer();
//        player.setGold(500);
        player.setPrimaryStage(primaryStage);
        GuiHelper.Scenes.startRestScene(player);
    }
}
