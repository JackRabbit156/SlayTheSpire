package view.gui.layouts.treasure_layout;

import helper.Color;
import helper.ConsoleAssistent;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import view.gui.TreasureView;

/**
 * Die Klasse EntryLayout verwaltet die grafische Darstellung des Einstiegslayouts im Schatz-Layout.
 * Sie zeigt das Spieler- und Schatzbild an und ermöglicht es dem Spieler, mit dem Schatzbild zu interagieren.
 *
 * @author Vladislav Keil
 */
public class EntryLayout extends HBox {
    private String playerImagePath;
    private TreasureView parentView;
    private VBox vTreasure;
    private Image treasureImg;
    private ImageView treasureImgView;

    /**
     * Konstruktor für die Klasse EntryLayout.
     * Initialisiert das Layout mit der Schatz-Ansicht und dem Spielerbildpfad.
     *
     * @param parentView      Die Schatz-Ansicht, in der das Layout angezeigt wird.
     * @param playerImagePath Der Pfad zum Spielerbild.
     */
    public EntryLayout(TreasureView parentView, String playerImagePath) {
        this.parentView = parentView;
        this.playerImagePath = playerImagePath;
        this.setTreasureImageView(false);
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
        vPlayer.setTranslateX(150);
        vPlayer.getChildren().add(getPlayerImageView());
        getChildren().add(vPlayer);
    }

    /**
     * Initialisiert den rechten Bereich des Layouts.
     * Fügt das Schatzbild dem Layout hinzu und setzt den Klick-Handler.
     */
    private void initRight() {
        this.vTreasure = new VBox();
        HBox.setHgrow(this.vTreasure, Priority.ALWAYS);
        this.vTreasure.setAlignment(Pos.CENTER);
        this.vTreasure.setTranslateY(50);
        this.vTreasure.setTranslateX(-150);

        this.treasureImgView.setOnMouseClicked(event -> {
            ConsoleAssistent.println(Color.YELLOW, "Clicked on Treasure");
            setTreasureImageView(true);
            onTreasureClick();
            this.treasureImgView.setDisable(true);
        });
        this.vTreasure.getChildren().add(this.treasureImgView);
        getChildren().add(this.vTreasure);
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

    /**
     * Setzt das Schatzbild basierend auf dem geöffneten oder geschlossenen Zustand.
     *
     * @param open True, wenn das Schatzbild geöffnet sein soll, andernfalls False.
     */
    private void setTreasureImageView(boolean open) {
        if (!open) {
            this.treasureImg = new Image(getClass().getResource("/images/treasure/treasure.png").toExternalForm());
        } else {
            ConsoleAssistent.println(Color.YELLOW, "Treasure Open");
            this.treasureImg = new Image(getClass().getResource("/images/treasure/treasureOpen.png").toExternalForm());
        }
        this.treasureImgView = new ImageView(this.treasureImg);
    }

    /**
     * Event-Handler für Klicks auf das Schatzbild.
     * Aktualisiert das Layout und ruft die entsprechende Methode der Schatz-Ansicht auf.
     */
    private void onTreasureClick() {
        getChildren().remove(0, 2);
        init();
        this.parentView.onTreasureClick();
    }
}
