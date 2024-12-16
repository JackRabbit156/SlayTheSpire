package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.gui.CreditView;
import de.bundeswehr.auf.slaythespire.gui.MainMenuView;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.helper.MusicBoy;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;
import de.bundeswehr.auf.slaythespire.model.settings.structure.GameMode;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Controller für die Darstellung des Hauptmenüs
 *
 * @author Loeschner, Marijan
 */
public class MainMenuController implements Controller {

    private final CreditView creditView = new CreditView();
    private Stage menuStage;
    private final MainMenuView view = new MainMenuView();

    public MainMenuController() {
        MusicBoy.play("mainMenu");

        // to remove relation to previous Save File
        GameSettings.lastSession = "";
        GameSettings.resetStats();
        GameSettings.setGameMode(GameMode.NORMAL);
    }

    public void credits(Button creditButton) {
        creditButton.setOnMouseClicked(event -> menuStage.getScene().setRoot(creditView.display()));
        creditView.getBackButton().setOnMouseClicked(event -> menuStage.getScene().setRoot(view.display()));

    }

    public void deleteSaveGame(Button dsg) {
        dsg.setOnMouseClicked(event -> GuiHelper.Scenes.startDeleteMenuScene(menuStage));
    }

    public void loadGame(Button loadButton) {
        loadButton.setOnMouseClicked(event -> GuiHelper.Scenes.startLoadGameFromMenuScene(menuStage));
    }

    public void newGame(Button newGameButton) {
        BooleanProperty difficultySet = new SimpleBooleanProperty(false);
        BooleanProperty modeSet = new SimpleBooleanProperty(false);

        Popup newGamePopup = new Popup();
        newGamePopup.setAutoHide(true);
        newGamePopup.getContent().add(view.displayDiffModeMessage());

        newGameButton.setOnMouseClicked(event -> newGamePopup.show(menuStage));

        view.getSuperEasyDifficulty().setOnMouseClicked(event -> {
            view.setDifficultyButton(view.getSuperEasyDifficulty());
            GameSettings.setDifficultyLevel(DifficultyLevel.SUPER_EASY);
            difficultySet.set(true);
        });

        view.getEasyDifficulty().setOnMouseClicked(event -> {
            view.setDifficultyButton(view.getEasyDifficulty());
            GameSettings.setDifficultyLevel(DifficultyLevel.EASY);
            difficultySet.set(true);
        });

        view.getNormalDifficulty().setOnMouseClicked(event -> {
            view.setDifficultyButton(view.getNormalDifficulty());
            GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);
            difficultySet.set(true);
        });

        view.getNormalMode().setOnMouseClicked(event -> {
            view.setModeButton(view.getNormalMode());
            GameSettings.setGameMode(GameMode.NORMAL);
            modeSet.set(true);
        });

        view.getHardcoreMode().setOnMouseClicked(event -> {
            view.setModeButton(view.getHardcoreMode());
            GameSettings.setGameMode(GameMode.HARDCORE);
            modeSet.set(true);
        });

        view.getContinueButton().disableProperty().bind(Bindings.createBooleanBinding(() -> !(difficultySet.get() && modeSet.get()), difficultySet, modeSet));
        view.getContinueButton().setOnMouseClicked(event -> {
            newGamePopup.hide();
            GuiHelper.Scenes.startCharSelection(menuStage);
        });
    }

    public void quit(Button quit) {
        quit.setOnMouseClicked(event -> GuiHelper.Scenes.requestClose(menuStage));
    }

    public BorderPane startMenu(Stage stage) {
        menuStage = stage;
        view.display();

        quit(view.getQuitButton());
        credits(view.getCreditsButton());
        deleteSaveGame(view.getDelSaveGameButton());
        loadGame(view.getLoadGameButton());
        newGame(view.getNewGameButton());
        return view.display();
    }

}
