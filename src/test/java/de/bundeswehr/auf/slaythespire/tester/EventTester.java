package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.potion.ExplosivePotion;
import javafx.application.Application;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.model.player.IroncladPlayer;

public class EventTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer(primaryStage);

        player.setGold(500);

        player.setCurrentHealth(500);

        player.addPotionCard(new ExplosivePotion());
        player.addPotionCard(new ExplosivePotion());
//        player.addPotionCard(new ExplosivePotion());

        player.setCurrentAct(1);

        GuiHelper.Scenes.startEventScene(player);
    }

}
