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
import view.gui.layouts.loot_layout.PlayerLayout;

/**
 * Die Klasse StatisticView verwaltet die grafische Darstellung der Statistik-Ansicht im Spiel.
 * Sie zeigt verschiedene Statistiken des Spielers an und ermöglicht es dem Spieler,
 * zur Kartenansicht zurückzukehren.
 *
 * @autor Vladislav Keil
 */
public class StatisticView extends StackPane {
    private VBox centerVBox;
    private Player player;
    private int act;

    private BorderPane statisticLayout;
    private BorderPane bottomLayout;
    private BorderPane backgroundLayout;

    /**
     * Konstruktor für die Klasse StatisticView.
     * Initialisiert die Ansicht mit einem Spieler und setzt den aktuellen Akt des Spielers.
     *
     * @param player Der Spieler, dessen Statistiken angezeigt werden.
     */
    public StatisticView(Player player) {
        this.statisticLayout = new BorderPane();
        this.bottomLayout = new BorderPane();
        this.backgroundLayout = new BorderPane();
        this.player = player;
        this.act = this.player.getCurrentAct();
        switch (this.act) {
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
        setBackground(new Background(GuiHelper.background("/images/act1.png")));

        getChildren().add(this.backgroundLayout);
        getChildren().add(this.statisticLayout);
        getChildren().add(this.bottomLayout);

        initBackgroundLayout();
        initStatisticLayout();
        initBottomLayout();
    }

    private void initBackgroundLayout() {
        this.backgroundLayout.setPickOnBounds(false);
        PlayerLayout playerLayout = new PlayerLayout(player.getImagePath());
        setBackground(new Background(GuiHelper.background("/images/act1.png")));

        this.backgroundLayout.setCenter(playerLayout);
    }

    /**
     * Initialisiert das untere Layout der Statistik-Ansicht.
     */
    private void initBottomLayout() {
        statisticLayout.setPickOnBounds(false);
        initBottom();
    }

    /**
     * Initialisiert das Layout der Statistik-Ansicht.
     */
    private void initBottom() {
        Image img = new Image(getClass().getResource("/images/buttons/buttonL.png").toExternalForm());
        ImageView imgView = new ImageView(img);
        HBox bottomHBox = new HBox();

        Label label = new Label("Back");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 38px; -fx-font-family: Kreon;");
        bottomHBox.getChildren().add(GuiHelper.addButtonStackPane(imgView, label, 14));

        imgView.setOnMouseClicked(event -> {
            imgView.setDisable(true);
            label.setDisable(true);
            GuiHelper.Scenes.startMapScene(player);
        });
        label.setOnMouseClicked(event -> {
            imgView.setDisable(true);
            label.setDisable(true);
            GuiHelper.Scenes.startMapScene(player);
        });

        bottomHBox.setAlignment(Pos.TOP_CENTER);
        bottomLayout.setBottom(bottomHBox);
    }

    /**
     * Initialisiert das zentrale Layout der Statistik-Ansicht.
     */
    private void initStatisticLayout() {
        bottomLayout.setPickOnBounds(false);
        initTop();
        initCenter();
    }

    /**
     * Initialisiert das obere Layout der Statistik-Ansicht.
     */
    private void initCenter(){
        centerVBox = new VBox();
        centerVBox.setSpacing(-150);
        Image btnImage = new Image(getClass().getResource("/images/panel/rewardPanel.png").toExternalForm());
        ImageView rewardPanelImgView = new ImageView(btnImage);
        StackPane rewardStackPanel = new StackPane(rewardPanelImgView);

        // LeftTextSide
        Label centerLeft = new Label();
        centerLeft.setTextFill(Paint.valueOf("White"));
        centerLeft.setWrapText(true);
        centerLeft.setStyle("-fx-font-size: 32px; -fx-font-family: Kreon;");

        centerLeft.setText("Act:\n" + "Damage dealt:\n" + "Damage received:\n" + "Gold received:\n" + "Energy spent:\n");
        centerLeft.setAlignment(Pos.CENTER_LEFT);

        // RightTextSide
        Label centerRight = new Label();
        centerRight.setStyle("-fx-font-size: 32px; -fx-font-family: Kreon;");
        centerRight.setTextFill(Paint.valueOf("White"));
        centerRight.setWrapText(true);
        centerRight.setAlignment(Pos.CENTER_RIGHT);
        centerRight.setText(
                this.act + "\n" +
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
     * Initialisiert das untere Layout der Statistik-Ansicht.
     */
    private void initTop(){
        Image img = new Image(getClass().getResource("/images/banner/abandon.png").toExternalForm());
        ImageView imageView = new ImageView(img);
        StackPane titlePane = new StackPane();

        VBox topVBox = new VBox();
        Label label = new Label();
        label.setText("Statistic");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 38px;-fx-font-family: Kreon;");

        titlePane.getChildren().addAll(imageView,label);


        topVBox.getChildren().add(titlePane);
        topVBox.setAlignment(Pos.BOTTOM_CENTER);
        topVBox.setPrefHeight(100);
        statisticLayout.setTop(topVBox);
    }
}
