package de.bundeswehr.auf.slaythespire.gui;

import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.gui.layouts.loot.PlayerLayout;

/**
 * Die Klasse StatisticView verwaltet die grafische Darstellung der Statistik-Ansicht im Spiel.
 * Sie zeigt verschiedene Statistiken des Spielers an und ermöglicht es dem Spieler,
 * zur Kartenansicht zurückzukehren.
 *
 * @author Vladislav Keil
 */
public class StatisticsView extends StackPane {

    private int act;
    private final BorderPane backgroundLayout;
    private final BorderPane bottomLayout;
    private final Player player;
    private final BorderPane statisticLayout;

    /**
     * Konstruktor für die Klasse StatisticView.
     * Initialisiert die Ansicht mit einem Spieler und setzt den aktuellen Akt des Spielers.
     *
     * @param player Der Spieler, dessen Statistiken angezeigt werden.
     */
    public StatisticsView(Player player) {
        this.player = player;

        statisticLayout = new BorderPane();
        bottomLayout = new BorderPane();
        backgroundLayout = new BorderPane();

        act = player.getCurrentAct();
        switch (act) {
            case 1:
                player.setCurrentAct(2);
                break;
            case 2:
            case 4:
                player.setCurrentAct(4);
                break;
        }
        LoggingAssistant.log(player.getCurrentAct() + " act");
        display();
    }

    /**
     * Initialisiert die View.
     */
    public void display() {
        setBackground(new Background(GuiHelper.backgroundInHD(player.getActImage())));

        getChildren().add(backgroundLayout);
        getChildren().add(statisticLayout);
        getChildren().add(bottomLayout);

        initBackgroundLayout();
        initStatisticLayout();
        initBottomLayout();
    }

    private void initBackgroundLayout() {
        this.backgroundLayout.setPickOnBounds(false);
        PlayerLayout playerLayout = new PlayerLayout(player.getImagePath());
        setBackground(new Background(GuiHelper.backgroundInHD(player.getActImage())));

        this.backgroundLayout.setCenter(playerLayout);
    }

    /**
     * Initialisiert das Layout der Statistik-Ansicht.
     */
    private void initBottom() {
        Image img = new Image("/images/buttons/golden_border.png");
        ImageView imgView = new ImageView(img);
        HBox bottomHBox = new HBox();

        Label label = new Label("Back");
        label.setTextFill(Color.WHITE);
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
     * Initialisiert das untere Layout der Statistik-Ansicht.
     */
    private void initBottomLayout() {
        statisticLayout.setPickOnBounds(false);
        initBottom();
    }

    /**
     * Initialisiert das obere Layout der Statistik-Ansicht.
     */
    private void initCenter() {
        VBox center = new VBox();
        Image btnImage = new Image(getClass().getResource("/images/panel/reward.png").toExternalForm());
        ImageView rewardPanelImgView = new ImageView(btnImage);
        StackPane rewardStackPanel = new StackPane(rewardPanelImgView);

        // LeftTextSide
        Label centerLeft = new Label();
        centerLeft.setTextFill(Color.WHITE);
        centerLeft.setWrapText(true);
        centerLeft.setStyle("-fx-font-size: 32px; -fx-font-family: Kreon;");

        centerLeft.setText(
                "Act:\n" +
                "Damage dealt:\n" +
                "Damage received:\n" +
                "Gold received:\n" +
                "Energy spent:");

        // RightTextSide
        Label centerRight = new Label();
        centerRight.setStyle("-fx-font-size: 32px; -fx-font-family: Kreon;");
        centerRight.setTextFill(Color.WHITE);
        centerRight.setWrapText(true);
        centerRight.setTextAlignment(TextAlignment.RIGHT);
        centerRight.setText(
                act + "\n" +
                GameSettings.getDistributedDamageStats() + "\n" +
                GameSettings.getReceivedDamageStats() + "\n" +
                GameSettings.getReceivedGoldStats() + "\n" +
                GameSettings.getEnergySpentStats()
        );

        // Textbox
        HBox textBox = new HBox();
        textBox.setSpacing(50);
        textBox.setAlignment(Pos.CENTER);
        textBox.getChildren().addAll(centerLeft, centerRight);
        rewardStackPanel.getChildren().add(textBox);
        rewardStackPanel.setAlignment(Pos.CENTER);
        center.getChildren().add(rewardStackPanel);
        centerLeft.setAlignment(Pos.CENTER);

        statisticLayout.setCenter(center);
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
     * Initialisiert das untere Layout der Statistik-Ansicht.
     */
    private void initTop() {
        Image img = new Image(getClass().getResource("/images/banner/abandon.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        Label label = new Label("Statistics");
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-font-size: 38px;-fx-font-family: Kreon;");

        StackPane titlePane = new StackPane();
        titlePane.getChildren().addAll(imageView, label);

        VBox top = new VBox();
        top.getChildren().add(titlePane);
        top.setAlignment(Pos.BOTTOM_CENTER);
        top.setPrefHeight(100);
        statisticLayout.setTop(top);
    }

}
