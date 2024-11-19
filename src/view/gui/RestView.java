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

/**
 * @author Keil, Vladislav
 */
public class RestView extends BorderPane {
    private RestViewEvents restViewEvents;

    private Insets insets = new Insets(15,15,15,15);
    private VBox centerVBox;
    private VBox topVBox;
    private Popup popup;


    public RestView(RestViewEvents restViewEvents) {
        this.restViewEvents = restViewEvents;
        display();
    }

    public void initRestViewEvents(RestViewEvents restViewEvents){
        this.restViewEvents = restViewEvents;
    }

    /**
     * Initialisiert die View.
     */
    public void display() {
        setBackground(new Background(GuiHelper.background("/images/backgrounds/RestViewBG.jpeg")));
        initTop();
        initBottom();
        initLeft();
        initRight();
        initCenter();
    }

    private void initCenter(){
        centerVBox = new VBox();
        Image img = new Image(getClass().getResource("/images/buttons/blankButton.png").toExternalForm());
        ImageView imgView = new ImageView(img);
        
        // Options
        Label label = new Label("Rest - 30% Healing");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24;");

        centerVBox.getChildren().add(GuiHelper.addButtonStackPane(imgView, label, 0.35, 0.25));
        label.setOnMouseClicked(event -> restViewEvents.onHealClicked());
        imgView.setOnMouseClicked(event -> restViewEvents.onHealClicked());

        centerVBox.setSpacing(50);
        centerVBox.setPadding(new Insets(50,15,15,280));
        centerVBox.setAlignment(Pos.TOP_CENTER);
        setCenter(centerVBox);
    }

    private void initTop(){
        topVBox = new VBox();
        Label label = new Label();
        label.setText("Restsite..");
        label.setId("title");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 56px;");

        topVBox.getChildren().add(label);
        topVBox.setAlignment(Pos.BOTTOM_CENTER);
        topVBox.setPrefHeight(200);

        setTop(topVBox);
    }


    /**
     * Bottom side
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

        bottomHBox.setAlignment(Pos.CENTER);
        Region placeHolder = new Region();
        placeHolder.setPrefHeight(150); // Festlegen der konstanten Höhe
        bottomHBox.getChildren().add(placeHolder);
        setBottom(bottomHBox);
    }

    /**
     * Left side
     */
    private void initLeft(){
        VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.CENTER);
        Region placeHolder = new Region();
        placeHolder.setPrefWidth(200); // Festlegen der konstanten Höhe
        leftVBox.getChildren().add(placeHolder);
        setLeft(leftVBox);
    }

    /**
     * Right side
     */
    private void initRight(){
        VBox rightVBox = new VBox();
        rightVBox.setAlignment(Pos.CENTER);
        Region bottomPlaceholder = new Region();
        bottomPlaceholder.setPrefWidth(200); // Festlegen der konstanten Höhe
        rightVBox.getChildren().add(bottomPlaceholder);
        setRight(rightVBox);
    }
}