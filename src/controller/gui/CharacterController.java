package controller.gui;

import helper.GuiHelper;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.player.IroncladPlayer;
import models.player.SilentPlayer;
import models.player.player_structure.Player;
import view.gui.CharacterView;

public class CharacterController {
    private CharacterView cv = new CharacterView();
    private Player player;
    //TODO: Player je nach Auswahl erstellen

    private Stage charStage;

    public BorderPane startSelection(Stage stage){
        charStage = stage;
        cv.display();

        cv.getBacks().setOnMouseClicked(event -> {
            GuiHelper.Scenes.startMainMenuScene(charStage);
        });
        cv.getEmbark().setOnMouseClicked(event -> {
            //TODO: Nur nach auswahl eines characters gehts weiter
            if(cv.getIc().isSelected()) {
                player = new IroncladPlayer();
                GuiHelper.Scenes.startMapScene(player, false);
            }
            else if(cv.getSl().isSelected()){
                player = new SilentPlayer();
                GuiHelper.Scenes.startMapScene(player, false);
            }
        });

        cv.getIc().setOnMouseClicked(event -> {
            cv.getIc().setBackground(cv.getIcHighlight());
            cv.selectIC();
        });
        cv.getSl().setOnMouseClicked(event -> {
            cv.getSl().setBackground(cv.getSlHighlight());
            cv.selectSL();
        });
        return cv.display();
    }
}
