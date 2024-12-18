package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.potion.common.ExplosivePotion;
import javafx.application.Application;
import javafx.stage.Stage;

public class EventTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = new TestPlayer(primaryStage);

        player.setGold(500);

        player.setCurrentHealth(500);

        player.addPotion(new ExplosivePotion());
        player.addPotion(new ExplosivePotion());
//        player.addPotion(new ExplosivePotion());

        player.setCurrentAct(1);

        GuiHelper.Scenes.startEventScene(player);
    }

}
