package app;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import models.card.DeckFactory;
import models.player.IroncladPlayer;
import models.potion.BloodPotion;
import models.potion.EnergyPotion;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        IroncladPlayer player = new IroncladPlayer();
        player.setPrimaryStage(primaryStage);

        player.addPotionCard(new EnergyPotion());
        player.addPotionCard(new BloodPotion());
        player.addPotionCard(new EnergyPotion());

//        GuiHelper.Scenes.startLoadSaveStateScene(primaryStage);
        GuiHelper.Scenes.startMapScene(player, true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}