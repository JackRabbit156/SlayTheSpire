package controller.gui;

import helper.GuiHelper;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.player.player_structure.Player;
import view.gui.CharacterView;

public class CharacterController {
    private CharacterView cv = new CharacterView();
    //TODO: Player je nach Auswahl erstellen
    //Player player = new Player() {

    private Stage charStage;

    public BorderPane startSelection(Stage stage){
        charStage = stage;
        cv.display();

        cv.getBacks().setOnMouseClicked(event -> {
            GuiHelper.Scenes.startMainMenuScene(charStage);
        });
        cv.getEmbark().setOnMouseClicked(event -> {
            //TODO: Nur nach auswahl eines characters gehts weiter
            if(cv.getIc().isArmed() || cv.getSl().isArmed()){
                //GuiHelper.Scenes.startMapScene(player, true);
            } else {
                //Hier dem Spieler mitteilen, dass er dumm ist.
            }
        });
        return cv.display();
    }
}
