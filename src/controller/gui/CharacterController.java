package controller.gui;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.gui.CharacterView;

public class CharacterController {
    CharacterView cv = new CharacterView();
    private Stage charStage;

    public BorderPane startSelection(Stage stage){
        charStage = stage;
        cv.display();
        return cv.display();
    }
}
