package de.bundeswehr.auf.slaythespire.gui.layouts.map;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.gui.MapView;
import de.bundeswehr.auf.slaythespire.gui.components.FullScreenButton;
import de.bundeswehr.auf.slaythespire.gui.components.SettingsButton;

/**
 * Die Klasse 'TopBarLayout' stellt die oberste Leiste der Map dar .
 * Diese Leiste zeigt wichtige Spielerinformationen wie
 * den Namen des Spielers, den aktuellen Gesundheitszustand, den Goldbetrag
 * und die aktuelle Etage an. Sie enthält auch Schaltflächen für Einstellungen
 * und Vollbildmodus.
 *
 * <p>
 * Die Klasse erstellt eine horizontale Anordnung von Labels und Bildern,
 * die sich an den entsprechenden Seiten der Leiste befinden. Die Klasse
 * behandelt auch Mausklickereignisse für die Schaltflächen in der oberen Leiste.
 * </p>
 *
 * @author Warawa Alexander
 */
public class TopBarLayout extends HBox {

    private static final Font font = Font.loadFont(TopBarLayout.class.getResourceAsStream(GuiHelper.DEFAULT_FONT_BOLD), 24);

    private final MapView mapView;
    private final Player player;

    /**
     * Konstruktor für die Klasse 'TopBarLayout'.
     *
     * @param player  Der Spieler, dessen Informationen angezeigt werden
     * @param mapView Die MapView, die zur Anzeige der Karte verwendet wird
     */
    public TopBarLayout(Player player, MapView mapView) {
        this.mapView = mapView;
        this.player = player;

        initTopBar();
    }

    private HBox initCenter() {
        HBox centerSide = new HBox();
        centerSide.setAlignment(Pos.CENTER);
        HBox.setHgrow(centerSide, Priority.ALWAYS);

        ImageView deckImage = GuiHelper.image("/images/map/deck.png", 55, 55);
        Label deckSizeLabel = new Label("" + player.getDeck().size());
        deckSizeLabel.setTranslateZ(-100);
        deckSizeLabel.setFont(font);
        deckSizeLabel.setTextFill(Color.WHITE);
        //deckSizeLabel.setPadding(new Insets(0, 30, 0, 0));
        centerSide.getChildren().addAll(deckImage, deckSizeLabel);
        return centerSide;
    }

    private HBox initLeftSide() {
        HBox leftSide = new HBox();
        leftSide.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(leftSide, Priority.ALWAYS);

        Label playerNameLabel = new Label(player.getName());
        playerNameLabel.setFont(font);
        playerNameLabel.setTextFill(Color.WHITE);
        playerNameLabel.setPadding(new Insets(0, 30, 0, 0));

        ImageView floor = GuiHelper.image("/images/map/floor.png", 55, 55);
        Label playerFloorLabel = new Label("" + player.getCurrentField());
        playerFloorLabel.setFont(font);
        playerFloorLabel.setTextFill(Color.DARKGRAY);
        playerFloorLabel.setPadding(new Insets(0, 30, 0, 0));

        ImageView gold = GuiHelper.image("/images/map/panelGoldBag.png", 55, 55);
        Label playerMoneyLabel = new Label("" + player.getGold());
        playerMoneyLabel.setFont(font);
        playerMoneyLabel.setTextFill(Color.GOLD);
        playerMoneyLabel.setPadding(new Insets(0, 30, 0, 0));

        ImageView heart = GuiHelper.image("/images/map/panelHeart.png", 55, 55);
        Label playerHealthLabel = new Label("" + player.getCurrentHealth() + "/" + player.getMaxHealth());
        playerHealthLabel.setFont(font);
        playerHealthLabel.setTextFill(Color.RED);
        playerHealthLabel.setPadding(new Insets(0, 30, 0, 0));
        leftSide.getChildren().addAll(playerNameLabel, heart, playerHealthLabel, gold, playerMoneyLabel, floor, playerFloorLabel);
        return leftSide;
    }

    private HBox initRightSide() {
        HBox rightSide = new HBox();
        rightSide.setTranslateX(-20);
        rightSide.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(rightSide, Priority.ALWAYS);

        Button settings = new SettingsButton();
        settings.setOnAction(e -> mapView.clickedOnSettings());

        Button fullscreen = new FullScreenButton();
        fullscreen.setOnAction(e -> mapView.clickedOnFullscreen());

        rightSide.getChildren().addAll(fullscreen, settings);
        return rightSide;
    }

    private void initTopBar() {
        setPadding(new Insets(10, 0, 0, 50));
        setBackground(new Background(GuiHelper.backgroundInHD("/images/map/bar.png")));
        setPrefHeight(50);

        HBox leftSide = initLeftSide();
        HBox centerSide = initCenter();
        HBox rightSide = initRightSide();

        getChildren().addAll(leftSide, centerSide, rightSide);
    }

}
