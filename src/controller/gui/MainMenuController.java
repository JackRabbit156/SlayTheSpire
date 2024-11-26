package controller.gui;

import helper.GuiHelper;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.game_settings.structure.GameMode;
import view.gui.CreditView;
import view.gui.MainMenuView;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainMenuController {
    private MainMenuView view = new MainMenuView();
    private CreditView creditView = new CreditView();
    private Stage menuStage;
    private Popup quitUp = new Popup();
    private Popup newGameUp = new Popup();

    public MainMenuController() {
        // to remove relation to previous Save File
        GameSettings.lastSession = "";
        GameSettings.resetStats();
        GameSettings.setGameMode(GameMode.NORMAL);
    }

    public BorderPane startMenu(Stage stage){
        menuStage = stage;
        view.display();
    
        quit(view.getQuitButton());
        credits(view.getCreditsButton());
        deleteSaveGame(view.getDelSaveGameButton());
        loadGame(view.getLoadGameButton());
        newGame(view.getNewGameButton());
        return view.display();
    }

    public void newGame(Button newGameButton){
        AtomicBoolean diffFlag = new AtomicBoolean(false);
        AtomicBoolean modeFlag = new AtomicBoolean(false);

        newGameUp.setAutoHide(true);
        newGameUp.getContent().add(view.displayDiffModeMessage());


        newGameButton.setOnMouseClicked(event -> {
            newGameUp.show(menuStage);

            view.getSupereasyDifficulty().setOnMouseClicked(e1 -> {
                view.setDifficultyButton(view.getSupereasyDifficulty());
                GameSettings.setDifficultyLevel(DifficultyLevel.SUPEREASY);
                diffFlag.set(true);
            });

            view.getEasyDifficulty().setOnMouseClicked(e2 -> {
                view.setDifficultyButton(view.getEasyDifficulty());
                GameSettings.setDifficultyLevel(DifficultyLevel.EASY);
                diffFlag.set(true);
            });

            view.getNormalDifficulty().setOnMouseClicked(e3 -> {
                view.setDifficultyButton(view.getNormalDifficulty());
                GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);
                diffFlag.set(true);
            });

            view.getNormalMode().setOnMouseClicked(e4 -> {
                view.setModeButton(view.getNormalMode());
                GameSettings.setGameMode(GameMode.NORMAL);
                modeFlag.set(true);
            });

            view.getHardcoreMode().setOnMouseClicked(e5 -> {
                view.setModeButton(view.getHardcoreMode());
                GameSettings.setGameMode(GameMode.HARDCORE);
                modeFlag.set(true);
            });
        });



        view.getContinueButton().setOnMouseClicked(event1 -> {
            if (!diffFlag.get()) {
                GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);
            }
            if (!modeFlag.get()) {
                GameSettings.setGameMode(GameMode.NORMAL);
            }
            newGameUp.hide();

            GuiHelper.Scenes.startCharSelection(menuStage);
        });
    }

    public void loadGame(Button loadButton){
        loadButton.setOnMouseClicked(event ->  {
            GuiHelper.Scenes.startLoadGameFromMenuScene(menuStage);
        });
    }
    public void deleteSaveGame(Button dsg){
        dsg.setOnMouseClicked(event -> {
            GuiHelper.Scenes.startDeleteMenuScene(menuStage);
        });
    }
    public void credits(Button creditButton){
        creditButton.setOnMouseClicked(event -> {
            quitUp.hide();
            quitUp.getContent().remove(view.displayQuitMessage());
            menuStage.getScene().setRoot(creditView.display());
            });
            creditView.getBackButton().setOnMouseClicked(event1 -> {
                menuStage.getScene().setRoot(view.display());
            });

    }
    public void quit(Button quit){
        quit.setOnMouseClicked(event -> {
            quitUp.getContent().add(view.displayQuitMessage());
            quitUp.show(menuStage);

            view.getNo().setOnMouseClicked(event1 -> {
                quitUp.hide();
                quitUp.getContent().remove(view.displayQuitMessage());
            });
            view.getYes().setOnMouseClicked(event2 -> {
                System.exit(0);
            });
        });
    }

}
