package app;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import models.player.IroncladPlayer;
import models.player.player_structure.Player;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Player player = new IroncladPlayer(primaryStage);
        //GuiHelper.Scenes.startMainMenuScene(primaryStage);
        GuiHelper.Scenes.startEventScene(player);
    }

    public static void main(String[] args) {
        launch(args);
    }
}