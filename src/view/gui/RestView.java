package view.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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

    private BackgroundImage background() {
        Image backgroundImage = new Image(getClass().getResource("/RestViewBG.jpeg").toExternalForm());
        return new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, // Option: NO_REPEAT, REPEAT, REPEAT_X, REPEAT_Y
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
        );
    }

    private void initCenter(){
        // Options
        centerVBox = new VBox();
        Button healBtn = new Button("Heilung um 30%");
        centerVBox.getChildren().add(healBtn);
        healBtn.setOnAction(event -> restViewEvents.onHealClicked());
//        centerVBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("Red"),null, this.insets)));
        centerVBox.setSpacing(30);
        centerVBox.setPadding(insets);
        centerVBox.setAlignment(Pos.TOP_CENTER);
//        centerVBox.getChildren().add();

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
        HBox bottomHBox = new HBox();
        Button backBtn = new Button("Back");
        backBtn.setOnAction(event -> restViewEvents.onBackClicked());
        bottomHBox.getChildren().add(backBtn);
//        bottomHBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("Blue"),null, this.insets)));
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


    /**
     * Initialisiert die View.
     */
    public void display() {
        setBackground(new Background(background()));
        initTop();
        initBottom();
        initLeft();
        initRight();
        initCenter();
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