package app;

import helper.GuiHelper;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

//        GuiHelper.Scenes.startLoadSaveStateScene(primaryStage);
        GuiHelper.Scenes.startMapScene(player);
    }

    public static void main(String[] args) {
        launch(args);
    }

}