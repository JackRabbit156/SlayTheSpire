package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
    private final Text playerTextStroke = new Text();
    private final Text playerText = new Text();
    private final Text healthTextStroke = new Text();
    private final Text healthText = new Text();
    private final Text moneyTextStroke = new Text();
    private final Text moneyText = new Text();

    private ImageView heartIconView;
    private ImageView moneybagIconView;

    private final String playerStrokeColor = "#303739";
    private final String playerTextColor = "#f3f7f9";
    private final String healthStrokeColor = "#391a15";
    private final String healthTextColor = "#e18b81";
    private final String moneyStrokeColor = "#2d2605";
    private final String moneyTextColor = "#e9cd77";


    private final int strokeWidth = 3;


    private final Font font = Font.font("Kreon", FontWeight.BOLD, 25);
    private final Font playerFont = Font.font("Kreon", FontWeight.BOLD, 35);
    private final FontSmoothingType smoothingType = FontSmoothingType.GRAY;



    public InfoLayout() {
        initHeartIcon();
        initMoneybagIcon();

        initPlayerText();
        initHealthText();
        initMoneyText();

        StackPane player = new StackPane();
        HBox health = new HBox();
        HBox money = new HBox();
        StackPane healthTextPane = new StackPane();
        StackPane moneyTextPane = new StackPane();
        healthTextPane.getChildren().addAll(healthTextStroke, healthText);
        moneyTextPane.getChildren().addAll(moneyTextStroke, moneyText);

        player.getChildren().addAll(playerTextStroke, playerText);
        health.getChildren().addAll(heartIconView, healthTextPane);
        money.getChildren().addAll(moneybagIconView, moneyTextPane);

        player.setAlignment(Pos.CENTER);
        health.setAlignment(Pos.CENTER);
        money.setAlignment(Pos.CENTER);

        getChildren().addAll(player, health, money);

        setAlignment(Pos.CENTER);

        setTranslateY(-30);
        setTranslateX(30);
    }

    public void setPlayerText(String playerName) {
        playerTextStroke.setText(playerName);
        playerText.setText(playerName);
    }

    public void setHealthText(int current, int max) {
        String text = current + "/" + max;
        healthTextStroke.setText(text);
        healthText.setText(text);
    }

    public void setMoneyText(int moneyAmount) {
        moneyTextStroke.setText(String.valueOf(moneyAmount));
        moneyText.setText(String.valueOf(moneyAmount));
    }

    private void initMoneyText() {
        moneyTextStroke.setStroke(Paint.valueOf(moneyStrokeColor));
        moneyTextStroke.setStrokeWidth(strokeWidth);
        moneyText.setFill(Paint.valueOf(moneyTextColor));
        moneyTextStroke.setFont(font);
        moneyTextStroke.setFontSmoothingType(smoothingType);
        moneyText.setFont(font);
        moneyText.setFontSmoothingType(smoothingType);
    }

    private void initHealthText() {
        healthTextStroke.setStroke(Paint.valueOf(healthStrokeColor));
        healthTextStroke.setStrokeWidth(strokeWidth);
        healthText.setFill(Paint.valueOf(healthTextColor));
        healthTextStroke.setFont(font);
        healthTextStroke.setFontSmoothingType(smoothingType);
        healthText.setFont(font);
        healthText.setFontSmoothingType(smoothingType);
    }

    private void initPlayerText() {
        playerTextStroke.setStroke(Paint.valueOf(playerStrokeColor));
        playerTextStroke.setStrokeWidth(strokeWidth);
        playerText.setFill(Paint.valueOf(playerTextColor));
        playerTextStroke.setFont(playerFont);
        playerTextStroke.setFontSmoothingType(smoothingType);
        playerText.setFont(playerFont);
        playerText.setFontSmoothingType(smoothingType);
    }

    private void initMoneybagIcon() {
        String path = "/images/view/gui/layouts/battle/info/moneybag.png";
        Image moneybag = new Image(getClass().getResource(path).toExternalForm());
        moneybagIconView = new ImageView(moneybag);

//        moneybagIconView.setFitHeight(40);
//        moneybagIconView.setFitWidth(40);
    }

    private void initHeartIcon() {
        String path = "/images/view/gui/layouts/battle/info/heart.png";
        Image heart = new Image(getClass().getResource(path).toExternalForm());
        heartIconView = new ImageView(heart);

//        heartIconView.setFitHeight(40);
//        heartIconView.setFitWidth(40);
    }
}
