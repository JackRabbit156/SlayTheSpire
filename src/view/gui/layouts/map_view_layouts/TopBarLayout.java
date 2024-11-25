package view.gui.layouts.map_view_layouts;

import helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import models.player.player_structure.Player;
import view.gui.MapView;

public class TopBarLayout extends HBox {
    private MapView mapView;
    private Player player;
    public TopBarLayout(Player player, MapView mapView) {
        this.mapView = mapView;
        this.player = player;

        //setSpacing(30);
        initTopBar();
    }

    private void initTopBar() {
        //Font kreonFont = Font.font("Kreon", FontWeight.BOLD, 24);
        Font kreonFont = Font.loadFont(getClass().getResourceAsStream("/font/kreon/static/Kreon-Bold.ttf"), 24);
        setPadding(new Insets(10,0,0,50));

        HBox leftSide = new HBox();
        leftSide.setAlignment(Pos.CENTER_LEFT);
        HBox centerSide = new HBox();
        centerSide.setAlignment(Pos.CENTER);
        HBox rightSide = new HBox();
        rightSide.setAlignment(Pos.CENTER_RIGHT);

        HBox.setHgrow(leftSide, Priority.ALWAYS);
        HBox.setHgrow(centerSide, Priority.ALWAYS);
        HBox.setHgrow(rightSide, Priority.ALWAYS);
        setBackground(new Background(GuiHelper.background("/images/map/bar.png")));
        setPrefHeight(50);


        /* LEFT SIDE */
        Label playerNameLabel = new Label(player.getName());
        playerNameLabel.setFont(kreonFont);
        playerNameLabel.setTextFill(Color.WHITE);
        playerNameLabel.setPadding(new Insets(0, 30, 0, 0));

        ImageView floor = image("/images/map/floor.png", 55, 55);
        Label playerFloorLabel = new Label("" +player.getCurrentField());
        playerFloorLabel.setFont(kreonFont);
        playerFloorLabel.setTextFill(Color.DARKGRAY);
        playerFloorLabel.setPadding(new Insets(0, 30, 0, 0));

        ImageView gold = image("/images/map/panelGoldBag.png", 55, 55);
        Label playerMoneyLabel = new Label(""+player.getGold());
        playerMoneyLabel.setFont(kreonFont);
        playerMoneyLabel.setTextFill(Color.GOLD);
        playerMoneyLabel.setPadding(new Insets(0, 30, 0, 0));

        ImageView heart = image("/images/map/panelHeart.png", 55, 55);
        Label playerHealthLabel = new Label("" + player.getCurrentHealth() +"/" + player.getMaxHealth());
        playerHealthLabel.setFont(kreonFont);
        playerHealthLabel.setTextFill(Color.RED);
        playerHealthLabel.setPadding(new Insets(0, 30, 0, 0));

        /* CENTER SIDE */
        ImageView deckImage = image("/images/map/deck.png", 55, 55);
        Label deckSizeLabel = new Label(""+player.getDeck().size());
        deckSizeLabel.setTranslateZ(-100);
        deckSizeLabel.setFont(kreonFont);
        deckSizeLabel.setTextFill(Color.WHITE);
        //deckSizeLabel.setPadding(new Insets(0, 30, 0, 0));

        /* RIGHT SIDE */
        ImageView settings = image("/images/map/settings.png", 55, 55);

        ImageView fullscreen = image("/images/map/fullscreen.png", 40, 40);
        fullscreen.setTranslateX(-20);

        settings.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            mapView.clickedOnSettings();
        });

        fullscreen.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            mapView.clickedOnFullscreen();
        });

        leftSide.getChildren().addAll(playerNameLabel, heart, playerHealthLabel, gold, playerMoneyLabel, floor, playerFloorLabel);
        centerSide.getChildren().addAll(deckImage, deckSizeLabel);
        rightSide.getChildren().addAll(fullscreen, settings);

        getChildren().addAll(leftSide, centerSide, rightSide);
        //getChildren().addAll(playerNameLabel, heart, playerHealthLabel, gold, playerMoneyLabel, floor, playerFloorLabel, settings);
    }

    private ImageView image(String imagePath, int width, int height) {
        Image figureImage = new Image(getClass().getResource(imagePath).toExternalForm());
        ImageView imageViewFigure = new ImageView(figureImage);
        imageViewFigure.setFitWidth(width);
        imageViewFigure.setFitHeight(height);
        imageViewFigure.setPreserveRatio(true);
        return imageViewFigure;
    }
}
