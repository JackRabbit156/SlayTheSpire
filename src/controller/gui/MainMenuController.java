package controller.gui;

import helper.GuiHelper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import view.gui.CharacterView;
import view.gui.CreditView;
import view.gui.MainMenuView;

import java.util.Objects;

public class MainMenuController {
    private MainMenuView view = new MainMenuView();
    private CreditView creditView = new CreditView();
    private Stage menuStage;
    private Popup quitUp = new Popup();


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
        newGameButton.setOnMouseClicked(event -> {
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
