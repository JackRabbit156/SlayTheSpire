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
        TestPlayer player = new TestPlayer(primaryStage);

        GuiHelper.Scenes.startStatisticScene(player);
    }
}
