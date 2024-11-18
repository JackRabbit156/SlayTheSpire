package view.gui;

import helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

        Image btnImage = new Image(getClass().getResource("/images/buttons/panel.png").toExternalForm());
        ImageView btnImageView = new ImageView(btnImage);
        // Options
        Button healBtn = new Button("Heilung um 30%");
        healBtn.setGraphic(btnImageView);

        btnImageView.setScaleX(0.35);
        btnImageView.setScaleY(0.25);

        StackPane healBtnPane = new StackPane(btnImageView);
        Label label = new Label("Rest - 30% Healing");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24;");
        healBtnPane.getChildren().add(label);

        centerVBox.getChildren().add(healBtnPane);
        label.setOnMouseClicked(event -> restViewEvents.onHealClicked());
        btnImageView.setOnMouseClicked(event -> restViewEvents.onHealClicked());
        GuiHelper.setButtonHoverEffect(btnImageView, label);


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
        Image btnImage = new Image(getClass().getResource("/images/buttons/buttonL.png").toExternalForm());
        ImageView backImgView = new ImageView(btnImage);
        HBox bottomHBox = new HBox();
        Button backBtn = new Button();

        backImgView.setFitWidth(backBtn.getWidth());
        backImgView.setFitHeight(backBtn.getHeight());
        backImgView.setScaleX(0.7);
        backImgView.setScaleY(0.7);

        StackPane backBtnPane = new StackPane(backImgView);
        Label label = new Label("Back");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24;");
        backBtnPane.getChildren().add(label);
        bottomHBox.getChildren().add(backBtnPane);

        backImgView.setOnMouseClicked(event -> restViewEvents.onBackClicked());
        label.setOnMouseClicked(event -> restViewEvents.onBackClicked());
        GuiHelper.setButtonHoverEffect(backImgView, label);

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
//        leftVBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("Yellow"),null, this.insets)));
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
//        rightVBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("Green"),null, this.insets)));
        rightVBox.setAlignment(Pos.CENTER);
        Region bottomPlaceholder = new Region();
        bottomPlaceholder.setPrefWidth(200); // Festlegen der konstanten Höhe
        rightVBox.getChildren().add(bottomPlaceholder);
        setRight(rightVBox);
    }

    public void showDialog(String text) {
        popup = new Popup();
        HBox box = new HBox();
        Label label = new Label(text);
        label.setTextFill(Paint.valueOf("White"));
        label.autosize();
        box.setPrefHeight(150);
        box.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"), new BorderStrokeStyle(StrokeType.CENTERED, StrokeLineJoin.ROUND, null, 0,0, null),null,null)));
        box.setPrefWidth(250);
        box.setOpacity(0.95);
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(label);
        box.setBackground(new Background(new BackgroundFill(Paint.valueOf("Grey"), null, null)));

        popup.setAutoHide(true);
        popup.getContent().add(box);
        popup.show(centerVBox.getScene().getWindow(), 800, 500);
    }
}