package view.gui.layouts.treasure_layout;

import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import view.gui.TreasureView;

/**
 * Die Klasse BackLayout verwaltet die grafische Darstellung des Zurück-Buttons im Schatz-Layout.
 * Sie zeigt einen Button an, der es dem Spieler ermöglicht, zur vorherigen Ansicht zurückzukehren.
 *
 * @autor Vladislav Keil
 */
public class BackLayout extends HBox {
    private TreasureView view;

    /**
     * Konstruktor für die Klasse BackLayout.
     * Initialisiert das Layout mit der Schatz-Ansicht.
     *
     * @param view Die Schatz-Ansicht, in der das Layout angezeigt wird.
     */
    public BackLayout(TreasureView view) {
        this.view = view;
        this.setMouseTransparent(false);
        init();
    }

    /**
     * Initialisiert das Layout.
     */
    private void init() {
        initBackButton();
    }

    /**
     * Initialisiert den Zurück-Button.
     * Fügt den Button dem Layout hinzu und setzt die Ereignis-Handler.
     */
    private void initBackButton() {
        Image btnImage = new Image(getClass().getResource("/images/buttons/buttonL-small.png").toExternalForm());
        ImageView imgView = new ImageView(btnImage);

        Label label = new Label("Back");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24;  -fx-font-family: Kreon;");

        getChildren().add(GuiHelper.addButtonStackPane(imgView, label, 12, 8));

        imgView.setOnMouseClicked(event -> this.onBackClick());
        label.setOnMouseClicked(event -> this.onBackClick());

        setAlignment(Pos.CENTER);
//        setTranslateY(150);
    }

    /**
     * Event-Handler für Klicks auf den Zurück-Button.
     * Ruft die entsprechende Methode der Schatz-Ansicht auf.
     */
    private void onBackClick() {
        this.view.onBackClick();
    }
}
