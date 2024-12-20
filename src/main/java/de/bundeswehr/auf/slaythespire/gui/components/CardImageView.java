package de.bundeswehr.auf.slaythespire.gui.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardImageView extends ImageView {

    public CardImageView(String image) {
        super(new Image(image));
        setFitWidth(250);
        setFitHeight(250);
        setPreserveRatio(true);
        setTranslateY(60);
    }

}
