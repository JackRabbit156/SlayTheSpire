package view.gui;

import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import models.game_settings.GameSettings;
import models.player.player_structure.Player;
import view.gui.layouts.layout_events.StatisticViewEvents;

/**
 * @author Keil, Vladislav
 */
public class StatisticView extends BorderPane {
    private StatisticViewEvents statisticViewEvents;
    private VBox centerVBox;
    private Player player;

    public StatisticView(Player player) {
        this.player = player;
    }
    /**
     * Initialisiert die View.
     */
    public void display() {
        setBackground(new Background(GuiHelper.background("/images/backgrounds/greenBg.jpg")));
        initLeft();
        initRight();
        initCenter();
        initTop();
    }

    /**
     * init Center
     */
    private void initCenter(){
        centerVBox = new VBox();
        centerVBox.setSpacing(-150);
        Image btnImage = new Image(getClass().getResource("/images/panel/rewardPanel.png").toExternalForm());
        ImageView rewardPanelImgView = new ImageView(btnImage);
        StackPane rewardStackPanel = new StackPane(rewardPanelImgView);

        // LeftTextSide
        Label centerLeft = new Label();
        centerLeft.setStyle("-fx-font-size: 32;");
        centerLeft.setTextFill(Paint.valueOf("White"));
        centerLeft.setWrapText(true);
        centerLeft.setText("Act:\n" + "Distributed Damage:\n" + "Received Damage:\n" + "Received Gold:\n" + "Energy spent:\n");
        centerLeft.setAlignment(Pos.CENTER_LEFT);

        // RightTextSide
        Label centerRight = new Label();
        centerRight.setStyle("-fx-font-size: 32;");
        centerRight.setTextFill(Paint.valueOf("White"));
        centerRight.setWrapText(true);
        centerRight.setAlignment(Pos.CENTER_RIGHT);
        centerRight.setText(
                player.getCurrentAct() + "\n" +
                GameSettings.getDistributedDamageStats() + "\n" +
                GameSettings.getReceivedDamageStats() + "\n" +
                GameSettings.getReceivedGoldStats() + "\n" +
                GameSettings.getEnergySpentStats() + "\n"
        );

        // Textbox
        HBox textBox = new HBox();
        textBox.setSpacing(50);
        textBox.setAlignment(Pos.CENTER);
        textBox.getChildren().addAll(centerLeft, centerRight);
        rewardStackPanel.getChildren().add(textBox);
        rewardStackPanel.setAlignment(Pos.CENTER);
        centerVBox.getChildren().add(rewardStackPanel);
        centerLeft.setAlignment(Pos.CENTER);
        centerVBox.getChildren().add(createButton("Next Act"));
        setCenter(centerVBox);
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

    /**
     * Top side
     */
    private void initTop(){
        VBox topVBox = new VBox();
        Label label = new Label();
        label.setText("Statistic");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 56px;");

        topVBox.getChildren().add(label);
        topVBox.setAlignment(Pos.BOTTOM_CENTER);
        topVBox.setPrefHeight(100);
        setTop(topVBox);
    }

    /**
     * Create Button
     */
    private Node createButton(String labelText){
        Image btnImage = new Image(getClass().getResource("/images/buttons/buttonL.png").toExternalForm());
        ImageView imgView = new ImageView(btnImage);
        imgView.maxHeight(100);
        StackPane btnPane = new StackPane(imgView);
        Label label = new Label(labelText);
        Button btn = new Button();

        btn.setGraphic(imgView);

        // ImgView
        imgView.setFitWidth(btn.getWidth());
        imgView.setFitHeight(btn.getHeight());
        imgView.setScaleX(0.7);
        imgView.setScaleY(0.7);

        // Label
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24;");
        GuiHelper.setButtonHoverEffect(imgView, label);
        imgView.setOnMouseClicked(event -> GuiHelper.Scenes.startMapScene(player, true));
        label.setOnMouseClicked(event -> GuiHelper.Scenes.startMapScene(player, true));

        btnPane.getChildren().add(label);

        return btnPane;
    }
}
