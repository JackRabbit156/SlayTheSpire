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
public class StatisticView extends StackPane {
    private StatisticViewEvents statisticViewEvents;
    private VBox centerVBox;
    private Player player;

    private BorderPane statisticLayout;
    private BorderPane bottomLayout;

    public StatisticView(Player player) {
        this.statisticLayout = new BorderPane();
        this.bottomLayout = new BorderPane();
        this.player = player;
        switch (this.player.getCurrentAct()) {
            case 1: this.player.setCurrentAct(2); break;
            case 2:
            case 4: this.player.setCurrentAct(4); break;
        }
        System.out.println(player.getCurrentAct() + " act" );
        display();
    }
    /**
     * Initialisiert die View.
     */
    public void display() {
        setBackground(new Background(GuiHelper.background("/images/backgrounds/greenBg.jpg")));
        getChildren().add(statisticLayout);
        getChildren().add(bottomLayout);

        initStatisticLayout();
        initBottomLayout();


    }

    private void initBottomLayout() {
        statisticLayout.setPickOnBounds(false);
        initBottom();
    }

    private void initBottom() {
        Image img = new Image(getClass().getResource("/images/buttons/buttonL.png").toExternalForm());
        ImageView imgView = new ImageView(img);
        HBox bottomHBox = new HBox();

        Label label = new Label("Back");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24;");
        bottomHBox.getChildren().add(GuiHelper.addButtonStackPane(imgView, label, 0.7));

        imgView.setOnMouseClicked(event -> GuiHelper.Scenes.startMapScene(player, false));
        label.setOnMouseClicked(event -> GuiHelper.Scenes.startMapScene(player, false));

        bottomHBox.setAlignment(Pos.TOP_LEFT);
        bottomHBox.setTranslateY(150);
        bottomLayout.setBottom(bottomHBox);
    }

    private void initStatisticLayout() {
        bottomLayout.setPickOnBounds(false);
        initTop();
        initCenter();
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

        statisticLayout.setCenter(centerVBox);
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
        statisticLayout.setTop(topVBox);
    }

}
