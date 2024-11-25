package view.gui;

import helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Popup;
import view.gui.layouts.layout_events.RestViewEvents;

import java.util.Stack;


/**
 * Die Klasse RestView verwaltet die grafische Darstellung der Rast-Ansicht im Spiel.
 * Sie zeigt die Optionen für das Heilen und die Rückkehr zur Kartenansicht an und ermöglicht es
 * dem Spieler, mit diesen zu interagieren.
 *
 * @autor Vladislav Keil
 */
public class RestView extends StackPane {
    private RestViewEvents restViewEvents;

    private VBox centerVBox;

    private BorderPane restLayout;
    private BorderPane bottomLayout;

    /**
     * Konstruktor für die Klasse RestView.
     * Initialisiert die Ansicht und setzt die Ereignisse der Rast-Ansicht.
     *
     * @param restViewEvents Die Ereignisse der Rast-Ansicht.
     */
    public RestView(RestViewEvents restViewEvents) {
        this.restLayout = new BorderPane();
        this.bottomLayout = new BorderPane();
        this.restViewEvents = restViewEvents;
        display();
    }
    /**
     * Initialisiert die Ereignisse der Rast-Ansicht.
     *
     * @param restViewEvents Die Ereignisse der Rast-Ansicht.
     */
    public void initRestViewEvents(RestViewEvents restViewEvents){
        this.restViewEvents = restViewEvents;
    }

    /**
     * Initialisiert die View.
     */
    public void display() {
        getChildren().add(this.restLayout);
        getChildren().add(this.bottomLayout);

        setBackground(new Background(GuiHelper.background("/images/backgrounds/RestViewBG.jpeg")));
        initRestLayout();
        initBottomLayout();
    }

    /**
     * Initialisiert das untere Layout.
     */
    private void initBottomLayout() {
        this.restLayout.setPickOnBounds(false);
        initBottom();

    }

    /**
     * Initialisiert das Layout der Rast-Ansicht.
     */
    private void initRestLayout() {
        this.bottomLayout.setPickOnBounds(false);
        initTop();
        initCenter();
    }

    /**
     * Initialisiert das zentrale Layout der Rast-Ansicht.
     */
    private void initCenter(){
        this.centerVBox = new VBox();
        Image img = new Image(getClass().getResource("/images/buttons/blankButton.png").toExternalForm());
        ImageView imgView = new ImageView(img);

        // Options
        Label label = new Label("Rest - 30% Healing");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24; -fx-font-family: Kreon;");

        this.centerVBox.getChildren().add(GuiHelper.addButtonStackPane(imgView, label, 10, 6));
        this.centerVBox.setTranslateY(125);
        this.centerVBox.setTranslateX(125);
        label.setOnMouseClicked(event -> {
            this.restViewEvents.onHealClicked();
            label.setDisable(true);
            imgView.setDisable(true);
        });
        imgView.setOnMouseClicked(event -> {
            this.restViewEvents.onHealClicked();
            label.setDisable(true);
            imgView.setDisable(true);
        });
        this.centerVBox.setSpacing(50);
        this.centerVBox.setPadding(new Insets(50,50,50,50));
        this.centerVBox.setAlignment(Pos.TOP_CENTER);
        this.restLayout.setCenter(this.centerVBox);
    }

    /**
     * Initialisiert das obere Layout der Rast-Ansicht.
     */
    private void initTop(){
        VBox topVBox = new VBox();
        Label label = new Label();
        label.setText("Restsite..");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 56; -fx-font-family: Kreon;");

        topVBox.getChildren().add(label);
        topVBox.setAlignment(Pos.BOTTOM_CENTER);
        topVBox.setPrefHeight(200);

        this.restLayout.setTop(topVBox);
    }

    /**
     * Initialisiert das untere Layout der Rast-Ansicht.
     */
    private void initBottom(){
        Image img = new Image(getClass().getResource("/images/buttons/buttonL-small.png").toExternalForm());
        ImageView imgView = new ImageView(img);
        HBox bottomHBox = new HBox();

        Label label = new Label("Back");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 38px; -fx-font-family: Kreon;");
        bottomHBox.getChildren().add(GuiHelper.addButtonStackPane(imgView, label, 12, 8));

        imgView.setOnMouseClicked(event -> this.restViewEvents.onBackClicked());
        label.setOnMouseClicked(event -> this.restViewEvents.onBackClicked());

        bottomHBox.setAlignment(Pos.TOP_LEFT);
        bottomHBox.setPadding(new Insets(50, 50, 50, 50));
        this.bottomLayout.setBottom(bottomHBox);
    }
}