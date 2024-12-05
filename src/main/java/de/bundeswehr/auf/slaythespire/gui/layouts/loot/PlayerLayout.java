package de.bundeswehr.auf.slaythespire.gui.layouts.loot;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PlayerLayout extends HBox {
    private String playerImagePath;

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
     * Initialisiert den linken Bereich des Layouts.
     * Fügt das Spielerbild dem Layout hinzu.
     */
    private void initLeft() {
        VBox vPlayer = new VBox();
        HBox.setHgrow(vPlayer, Priority.ALWAYS);
        vPlayer.setAlignment(Pos.CENTER);
        vPlayer.setTranslateY(150);
        vPlayer.setTranslateX(-700);
        vPlayer.getChildren().add(getPlayerImageView());
        getChildren().add(vPlayer);
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

    /**
     * Erstellt und gibt ein ImageView für das Spielerbild zurück.
     *
     * @return Das ImageView für das Spielerbild.
     */
    private ImageView getPlayerImageView() {
        Image image = new Image(getClass().getResource(this.playerImagePath).toExternalForm());
        ImageView imageView = new ImageView(image);
        return imageView;
    }

}
