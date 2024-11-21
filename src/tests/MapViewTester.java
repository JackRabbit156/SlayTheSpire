package tests;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * @author Keil, Vladislav
 */
public class MapViewTester  extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer();
        player.setCurrentAct(2);
        player.setCurrentField("32");
        player.setPrimaryStage(primaryStage);
        GuiHelper.Scenes.startMapScene(player, true);
    }
}
