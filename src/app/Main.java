package app;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import models.card.ironclad_cards.IroncladCardEnum;
import models.card.ironclad_cards.attack.common.AngerCard;
import models.player.IroncladPlayer;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        IroncladPlayer player = new IroncladPlayer();
        player.setPrimaryStage(primaryStage);

       // System.out.println(new AngerCard().getClass().getSimpleName());
//        GuiHelper.Scenes.startLoadSaveStateScene(primaryStage);
        GuiHelper.Scenes.startMapScene(player, true);
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}