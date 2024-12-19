package de.bundeswehr.auf.slaythespire.gui.layouts.top_bar;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 * Das Info layout.
 * das zeug ganz links auf der grauen Leiste, Name, HP, Gold
 *
 * @author OF Daniel Willig
 */
public class InfoLayout extends HBox {

    private static final Font font = Font.font("Kreon", FontWeight.BOLD, 25);
    private static final FontSmoothingType smoothingType = FontSmoothingType.GRAY;
    private static final int strokeWidth = 3;

    private final Label floorLabel = new Label();
    private final Text healthText = new Text();
    private final Text healthTextStroke = new Text();
    private final Text moneyText = new Text();
    private final Text moneyTextStroke = new Text();
    private final Text playerText = new Text();
    private final Text playerTextStroke = new Text();

    public InfoLayout() {
        initPlayerText();
        StackPane player = new StackPane();
        player.getChildren().addAll(playerTextStroke, playerText);
        player.setAlignment(Pos.CENTER);

        Image heart = new Image("/images/view/gui/layouts/info/heart.png");
        ImageView heartIcon = new ImageView(heart);
        initHealthText();
        HBox health = new HBox();
        StackPane healthTextPane = new StackPane();
        healthTextPane.getChildren().addAll(healthTextStroke, healthText);
        health.getChildren().addAll(heartIcon, healthTextPane);
        health.setAlignment(Pos.CENTER);

        Image moneybag = new Image("/images/view/gui/layouts/info/moneybag.png");
        ImageView moneybagIconView = new ImageView(moneybag);
        initMoneyText();
        HBox money = new HBox();
        StackPane moneyTextPane = new StackPane();
        moneyTextPane.getChildren().addAll(moneyTextStroke, moneyText);
        money.getChildren().addAll(moneybagIconView, moneyTextPane);
        money.setAlignment(Pos.CENTER);

        ImageView floorImage = GuiHelper.image("/images/map/floor.png", 55, 55);
        initFloorText();
        HBox level = new HBox(floorImage, floorLabel);
        level.setAlignment(Pos.CENTER);

        getChildren().addAll(player, health, money, level);

        setAlignment(Pos.CENTER_LEFT);
    }

    public void update(Player player) {
        setPlayerText(player.getName());
        setMoneyText(player.getGold());
        setHealthText(player.getCurrentHealth(), player.getMaxHealth());
        setFloorLabel(player);
    }

    private void initFloorText() {
        floorLabel.setFont(font);
        floorLabel.setTextFill(Paint.valueOf("#E7D4AD"));
        floorLabel.setPadding(new Insets(0, 30, 0, 0));
    }

    private void initHealthText() {
        initStroke(font, healthTextStroke, "#391a15");
        initText(font, healthText, "#e18b81");
    }

    private void initMoneyText() {
        initStroke(font, moneyTextStroke, "#2d2605");
        initText(font, moneyText, "#e9cd77");
    }

    private void initPlayerText() {
        Font font = Font.font("Kreon", FontWeight.BOLD, 35);
        initStroke(font, playerTextStroke, "#303739");
        initText(font, playerText, "#f3f7f9");
    }

    private void initStroke(Font font, Text playerTextStroke, String s) {
        playerTextStroke.setStroke(Paint.valueOf(s));
        playerTextStroke.setStrokeWidth(strokeWidth);
        playerTextStroke.setFont(font);
        playerTextStroke.setFontSmoothingType(smoothingType);
    }

    private void initText(Font font, Text playerText, String s) {
        playerText.setFill(Paint.valueOf(s));
        playerText.setFont(font);
        playerText.setFontSmoothingType(smoothingType);
    }

    private void setFloorLabel(Player player) {
        floorLabel.setText(player.getCurrentField());
    }

    private void setHealthText(int current, int max) {
        String text = current + "/" + max;
        healthTextStroke.setText(text);
        healthText.setText(text);
    }

    private void setMoneyText(int moneyAmount) {
        String text = String.valueOf(moneyAmount);
        moneyTextStroke.setText(text);
        moneyText.setText(text);
    }

    private void setPlayerText(String playerName) {
        playerTextStroke.setText(playerName);
        playerText.setText(playerName);
    }

}
