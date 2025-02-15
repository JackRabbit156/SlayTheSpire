package de.bundeswehr.auf.slaythespire.app;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GuiHelper.Scenes.startMainMenuScene(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}