package tests;

import controller.gui.ShopController;
import helper.GuiHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.gui.StatisticView;

import java.util.Objects;

/**
 * @author Keil, Vladislav
 */
public class StatisticsViewTester extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer();
        StatisticView statisticView = new StatisticView(player);
        statisticView.display();
        Scene scene = new Scene(statisticView, 1920, 1080);

        player.setPrimaryStage(primaryStage);
        GuiHelper.Scenes.startScene(primaryStage, scene, "Slay the Spire - JavaFX");
    }
}
