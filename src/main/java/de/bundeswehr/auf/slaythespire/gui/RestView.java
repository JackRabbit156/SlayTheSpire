package de.bundeswehr.auf.slaythespire.gui;

import de.bundeswehr.auf.slaythespire.gui.events.RestViewEvents;
import de.bundeswehr.auf.slaythespire.gui.layouts.top_bar.TopBarLayout;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;


/**
 * Die Klasse RestView verwaltet die grafische Darstellung der Rast-Ansicht im Spiel.
 * Sie zeigt die Optionen für das Heilen und die Rückkehr zur Kartenansicht an und ermöglicht es
 * dem Spieler, mit diesen zu interagieren.
 *
 * @author Vladislav Keil
 */
public class RestView extends StackPane implements WithTopBar {

    private final BorderPane backLayout;
    private final Player player;
    private final BorderPane restLayout;
    private final RestViewEvents restViewEvents;
    private TopBarLayout top;

    /**
     * Konstruktor für die Klasse RestView.
     * Initialisiert die Ansicht und setzt die Ereignisse der Rast-Ansicht.
     *
     * @param player         Der Spieler
     * @param restViewEvents Die Ereignisse der Rast-Ansicht.
     */
    public RestView(Player player, RestViewEvents restViewEvents) {
        this.player = player;
        this.restViewEvents = restViewEvents;
        restLayout = new BorderPane();
        backLayout = new BorderPane();
        display();
    }

    @Override
    public void discard() {
        top.discard();
    }

    /**
     * Initialisiert die View.
     */
    public void display() {
        getChildren().add(restLayout);
        getChildren().add(backLayout);

        setBackground(new Background(GuiHelper.backgroundInHD("/images/backgrounds/RestViewBG.jpeg")));
        initRestLayout();
        initBackLayout();
    }

    @Override
    public void onFullScreen() {
        restViewEvents.onFullScreenClicked();
    }

    @Override
    public void onMap() {
        restViewEvents.onBackClicked();
    }

    @Override
    public boolean showMap() {
        return true;
    }

    /**
     * Initialisiert das untere Layout der Rast-Ansicht.
     */
    private void initBackLayout() {
        top = new TopBarLayout(this, player);
        backLayout.setTop(top);

        ImageView imgView = new ImageView(new Image("/images/buttons/buttonL-small.png"));

        Label label = new Label("Back");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 38px; -fx-font-family: Kreon;");

        imgView.setOnMouseClicked(event -> restViewEvents.onBackClicked());
        label.setOnMouseClicked(event -> restViewEvents.onBackClicked());

        HBox bottom = new HBox();
        bottom.getChildren().add(GuiHelper.addButtonStackPane(imgView, label, 12, 8));
        bottom.setAlignment(Pos.TOP_LEFT);
        bottom.setPadding(new Insets(50, 50, 50, 50));

        backLayout.setPickOnBounds(false);
        backLayout.setBottom(bottom);
    }

    /**
     * Initialisiert das zentrale Layout der Rast-Ansicht.
     */
    private void initCenter() {
        VBox center = new VBox();
        Image img = new Image(getClass().getResource("/images/buttons/blankButton.png").toExternalForm());
        ImageView imgView = new ImageView(img);

        // Options
        Label label = new Label("Rest - 30% Healing");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24; -fx-font-family: Kreon;");

        center.getChildren().add(GuiHelper.addButtonStackPane(imgView, label, 10, 6));
        center.setTranslateY(125);
        center.setTranslateX(125);
        label.setOnMouseClicked(event -> {
            restViewEvents.onHealClicked();
            label.setDisable(true);
            imgView.setDisable(true);
        });
        imgView.setOnMouseClicked(event -> {
            restViewEvents.onHealClicked();
            label.setDisable(true);
            imgView.setDisable(true);
        });
        center.setSpacing(50);
        center.setPadding(new Insets(50, 50, 50, 50));
        center.setAlignment(Pos.TOP_CENTER);
        restLayout.setCenter(center);
    }

    /**
     * Initialisiert das Layout der Rast-Ansicht.
     */
    private void initRestLayout() {
        restLayout.setPickOnBounds(false);
        initTop();
        initCenter();
    }

    /**
     * Initialisiert das obere Layout der Rast-Ansicht.
     */
    private void initTop() {
        VBox top = new VBox();
        Label label = new Label();
        label.setText("Rest Site...");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 56; -fx-font-family: Kreon;");

        top.getChildren().add(label);
        top.setAlignment(Pos.BOTTOM_CENTER);
        top.setPrefHeight(200);

        restLayout.setTop(top);
    }
}