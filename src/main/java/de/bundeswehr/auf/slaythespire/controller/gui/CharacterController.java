package de.bundeswehr.auf.slaythespire.controller.gui;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.models.game_settings.GameSettings;
import de.bundeswehr.auf.slaythespire.models.player.IroncladPlayer;
import de.bundeswehr.auf.slaythespire.models.player.SilentPlayer;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;
import de.bundeswehr.auf.slaythespire.gui.CharacterView;

/**
 * Controller für die Darstellung der Charaktererstellung
 * @author Loeschner, Marijan
 */
public class CharacterController {
    private CharacterView cv = new CharacterView();
    private Player player;

    private Stage charStage;

    public BorderPane startSelection(Stage stage){
        charStage = stage;
        cv.display();

        cv.getBacks().setOnMouseClicked(event -> {
            GuiHelper.Scenes.startMainMenuScene(charStage);
        });
        cv.getEmbark().setOnMouseClicked(event -> {
            if(cv.getIc().isSelected()) {
                player = new IroncladPlayer(charStage);
                GuiHelper.Scenes.startMapScene(player);
                GameSettings.startTimer();
            }
            else if(cv.getSl().isSelected()){
                player = new SilentPlayer(charStage);
                GuiHelper.Scenes.startMapScene(player);
                GameSettings.startTimer();
            }
        });

        cv.getIc().setOnMouseClicked(event -> {
            cv.getSl().setBackground(cv.getSlButtonBG());
            cv.selectIC();
        });
        cv.getSl().setOnMouseClicked(event -> {
            cv.getIc().setBackground(cv.getIcButtonBG());
            cv.selectSL();
        });
        return cv.display();
    }
}
