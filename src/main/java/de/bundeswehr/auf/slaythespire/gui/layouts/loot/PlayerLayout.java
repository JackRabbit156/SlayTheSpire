package de.bundeswehr.auf.slaythespire.gui.layouts.loot;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PlayerLayout extends HBox {

    private final String playerImagePath;

    public PlayerLayout(String playerImagePath) {
        this.playerImagePath = playerImagePath;
        init();
    }

    /**
     * Initialisiert das Layout.
     */
    public void init() {
        initLeft();
        initRight();
    }

    /**
     * Erstellt und gibt ein ImageView für das Spielerbild zurück.
     *
     * @return Das ImageView für das Spielerbild.
     */
    private ImageView getPlayerImageView() {
        Image image = new Image(playerImagePath);
        return new ImageView(image);
    }

    /**
     * Initialisiert den linken Bereich des Layouts.
     * Fügt das Spielerbild dem Layout hinzu.
     */
    private void initLeft() {
        VBox player = new VBox();
        HBox.setHgrow(player, Priority.ALWAYS);
        player.setAlignment(Pos.CENTER);
        player.setTranslateY(150);
        player.setTranslateX(-700);
        player.getChildren().add(getPlayerImageView());
        getChildren().add(player);
    }

    /**
     * Initialisiert den rechten Bereich des Layouts.
     * Fügt das Schatzbild dem Layout hinzu und setzt den Klick-Handler.
     */
    private void initRight() {
        VBox vBox = new VBox();
        vBox.getChildren().add(new Region());
        getChildren().add(vBox);
    }

}
