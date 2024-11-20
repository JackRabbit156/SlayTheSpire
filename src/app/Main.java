package app;

import controller.cli.menus.MainMenuViewController;
import controller.gui.LoadController;
import controller.gui.MapController;
import helper.GuiHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.enemy.Enemy;
import models.player.SilentPlayer;
import models.player.player_structure.Player;
import view.gui.LoadView;

import java.util.ArrayList;
import java.util.List;
import models.player.IroncladPlayer;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        IroncladPlayer player = new IroncladPlayer();
//        SilentPlayer player = new SilentPlayer();
        player.setPrimaryStage(primaryStage);

        GuiHelper.Scenes.startMapScene(primaryStage, player, true);

    }

    public static void main(String[] args) {
        launch(args);
    }

}