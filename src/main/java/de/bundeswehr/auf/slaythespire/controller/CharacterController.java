package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.gui.CharacterView;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.player.IroncladPlayer;
import de.bundeswehr.auf.slaythespire.model.player.SilentPlayer;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controller für die Darstellung der Charaktererstellung
 *
 * @author Loeschner, Marijan
 */
public class CharacterController implements Controller {

    private Stage charStage;
    private final CharacterView characterView = new CharacterView();
    private Player player;

    public BorderPane startSelection(Stage stage) {
        charStage = stage;
        characterView.display();

        characterView.getBacks().setOnMouseClicked(event -> {
            GuiHelper.Scenes.startMainMenuScene(charStage);
        });
        characterView.getEmbark().setOnMouseClicked(event -> {
            if (characterView.getIc().isSelected()) {
                player = new IroncladPlayer(charStage);
                GuiHelper.Scenes.startMapScene(player);
                GameSettings.startTimer();
            }
            else if (characterView.getSl().isSelected()) {
                player = new SilentPlayer(charStage);
                GuiHelper.Scenes.startMapScene(player);
                GameSettings.startTimer();
            }
        });

        characterView.getIc().setOnMouseClicked(event -> {
            characterView.getSl().setBackground(characterView.getSlButtonBG());
            characterView.selectIC();
        });
        characterView.getSl().setOnMouseClicked(event -> {
            characterView.getIc().setBackground(characterView.getIcButtonBG());
            characterView.selectSL();
        });
        return characterView.display();
    }
}
