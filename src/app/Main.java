package app;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import models.player.IroncladPlayer;
import models.player.player_structure.Player;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GuiHelper.Scenes.startMainMenuScene(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}