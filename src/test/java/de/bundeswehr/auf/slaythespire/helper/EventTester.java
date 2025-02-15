package de.bundeswehr.auf.slaythespire.helper;

import de.bundeswehr.auf.slaythespire.model.player.TestPlayer;
import de.bundeswehr.auf.slaythespire.model.potion.common.ExplosivePotion;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.Normal;
import javafx.application.Application;
import javafx.stage.Stage;

public class EventTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = TestPlayer.ironclad(primaryStage);

        player.setGold(500);

        player.setCurrentHealth(500);

        player.addPotion(new ExplosivePotion());
        player.addPotion(new ExplosivePotion());
//        player.addPotion(new ExplosivePotion());

        player.setCurrentAct(1);
        GameSettings.setDifficultyLevel(new Normal());

        GuiHelper.Scenes.startEventScene(player);
    }

}
