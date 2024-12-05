package de.bundeswehr.auf.slaythespire.gui.layouts.menu;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;

public class DifficultyLayout extends StackPane {
    private final Image backGroundImage = new Image(getClass().getResource("/images/view/gui/layouts/new_game_difficulty/background.png").toExternalForm());
    private final Image buttonImage = new Image(getClass().getResource("/images/view/gui/layouts/new_game_difficulty/nameBox.png").toExternalForm());
    private final Image yesImage = new Image(getClass().getResource("/images/view/gui/layouts/new_game_difficulty/background.png").toExternalForm());
    private final Image noImage = new Image(getClass().getResource("/images/view/gui/layouts/new_game_difficulty/background.png").toExternalForm());


    private final String strokeColor = "#000000";
    private final int strokeWidth = 3;

    private final String textColor = "#ffffff";

    private final Font font = Font.font("Kreon", FontWeight.BOLD, 20);
    private final FontSmoothingType smoothingType = FontSmoothingType.GRAY;



    public void DifficultyLayout() {
//        initImages();
//        initText();

    }



}
