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
        setPadding(new Insets(0,0,0,300));

        HBox leftSide = new HBox();
        HBox centerSide = new HBox();
        HBox rightSide = new HBox();
        rightSide.setAlignment(Pos.TOP_RIGHT);

        HBox.setHgrow(leftSide, Priority.ALWAYS);
        HBox.setHgrow(centerSide, Priority.ALWAYS);
        HBox.setHgrow(rightSide, Priority.ALWAYS);
        //setStyle("-fx-background-color: rgba(34, 34, 34, 0.8);");
        setBackground(new Background(GuiHelper.background("/images/map/bar.png")));
        setPrefHeight(50);

        Label playerNameLabel = new Label(player.getName());
        playerNameLabel.getStyleClass().add("name-label");

        ImageView floor = image("/images/map/floor.png");
        Label playerFloorLabel = new Label("" +player.getCurrentField());
        playerFloorLabel.getStyleClass().add("floor-label");

        ImageView gold = image("/images/map/panelGoldBag.png");
        Label playerMoneyLabel = new Label(""+player.getGold());
        playerMoneyLabel.getStyleClass().add("gold-label");

        ImageView heart = image("/images/map/panelHeart.png");
        Label playerHealthLabel = new Label("" + player.getCurrentHealth() +"/" + player.getMaxHealth());
        playerHealthLabel.getStyleClass().add("health-label");

        ImageView settings = image("/images/map/settings.png");

        settings.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println("Here it goes!");
            mapView.clickedOnSettings();
        });


        leftSide.getChildren().addAll(playerNameLabel, heart, playerHealthLabel, gold, playerMoneyLabel, floor, playerFloorLabel);
        rightSide.getChildren().addAll(settings);

        getChildren().addAll(leftSide, centerSide, rightSide);
        //getChildren().addAll(playerNameLabel, heart, playerHealthLabel, gold, playerMoneyLabel, floor, playerFloorLabel, settings);
    }

    private ImageView image(String imagePath) {
        Image figureImage = new Image(getClass().getResource(imagePath).toExternalForm());
        ImageView imageViewFigure = new ImageView(figureImage);
        imageViewFigure.setFitWidth(55);
        imageViewFigure.setFitHeight(55);
        imageViewFigure.setPreserveRatio(true);
        return imageViewFigure;
    }
}
