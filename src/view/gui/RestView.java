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
    private VBox topVBox;
    private Popup popup;

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
        getChildren().add(restLayout);
        getChildren().add(bottomLayout);

        setBackground(new Background(GuiHelper.background("/images/backgrounds/RestViewBG.jpeg")));
        initRestLayout();
        initBottomLayout();
    }

    /**
     * Initialisiert das untere Layout.
     */
    private void initBottomLayout() {
        restLayout.setPickOnBounds(false);
        initBottom();

    }

    /**
     * Initialisiert das Layout der Rast-Ansicht.
     */
    private void initRestLayout() {
        bottomLayout.setPickOnBounds(false);
        initTop();
        initCenter();
    }

    /**
     * Initialisiert das zentrale Layout der Rast-Ansicht.
     */
    private void initCenter(){
        centerVBox = new VBox();
        Image img = new Image(getClass().getResource("/images/buttons/blankButton.png").toExternalForm());
        ImageView imgView = new ImageView(img);

        // Options
        Label label = new Label("Rest - 30% Healing");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24; -fx-font-family: Kreon;");

        centerVBox.getChildren().add(GuiHelper.addButtonStackPane(imgView, label, 0.35, 0.25));
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
        centerVBox.setSpacing(50);
        centerVBox.setPadding(new Insets(50,15,15,280));
        centerVBox.setAlignment(Pos.TOP_CENTER);
        restLayout.setCenter(centerVBox);
    }

    /**
     * Initialisiert das obere Layout der Rast-Ansicht.
     */
    private void initTop(){
        topVBox = new VBox();
        Label label = new Label();
        label.setText("Restsite..");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 56; -fx-font-family: Kreon;");

        topVBox.getChildren().add(label);
        topVBox.setAlignment(Pos.BOTTOM_CENTER);
        topVBox.setPrefHeight(200);

        restLayout.setTop(topVBox);
    }

    /**
     * Initialisiert das untere Layout der Rast-Ansicht.
     */
    private void initBottom(){
        Image img = new Image(getClass().getResource("/images/buttons/buttonL.png").toExternalForm());
        ImageView imgView = new ImageView(img);
        HBox bottomHBox = new HBox();

        Label label = new Label("Back");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24;");
        bottomHBox.getChildren().add(GuiHelper.addButtonStackPane(imgView, label, 0.7));

        imgView.setOnMouseClicked(event -> restViewEvents.onBackClicked());
        label.setOnMouseClicked(event -> restViewEvents.onBackClicked());

        bottomHBox.setAlignment(Pos.TOP_LEFT);
        bottomHBox.setTranslateY(150);
        bottomLayout.setBottom(bottomHBox);
    }
}