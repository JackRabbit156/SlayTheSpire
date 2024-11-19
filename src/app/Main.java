package app;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import models.player.IroncladPlayer;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        IroncladPlayer player = new IroncladPlayer();
        player.setPrimaryStage(primaryStage);

        //GuiHelper.Scenes.startLoadSaveStateScene(primaryStage);
        GuiHelper.Scenes.startMapScene(player, true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}