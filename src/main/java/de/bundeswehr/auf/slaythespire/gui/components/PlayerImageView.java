package de.bundeswehr.auf.slaythespire.gui.components;

import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerImageView extends ImageView {

    public PlayerImageView(Player player) {
        super(new Image(player.getImagePath()));
        setPreserveRatio(true);
        setStyle("-fx-background-color: #926099;");
    }

}
