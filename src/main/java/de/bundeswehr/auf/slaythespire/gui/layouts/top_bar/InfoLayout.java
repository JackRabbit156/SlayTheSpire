package de.bundeswehr.auf.slaythespire.gui.layouts.top_bar;

import com.sun.javafx.scene.traversal.Direction;
import de.bundeswehr.auf.slaythespire.controller.listener.EmptyInventoryEventListener;
import de.bundeswehr.auf.slaythespire.controller.listener.EmptyPlayerEventListener;
import de.bundeswehr.auf.slaythespire.events.InventoryEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerDamageEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerHealthEvent;
import de.bundeswehr.auf.slaythespire.gui.components.*;
import de.bundeswehr.auf.slaythespire.helper.Animate;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * Das Info layout.
 * das zeug ganz links auf der grauen Leiste, Name, HP, Gold
 *
 * @author OF Daniel Willig
 */
public class InfoLayout extends HBox {

    private static final double FONT_SIZE = 25;
    private static final int STROKE_WIDTH = 3;

    private StrokedText floorText;
    private StrokedText healthText;
    private StrokedText moneyText;
    private StrokedText playerText;

    public InfoLayout(Player player) {
        initPlayerText();

        playerText.setAlignment(Pos.CENTER);

        Image heart = new Image("/images/view/gui/layouts/info/heart.png");
        ImageView heartIcon = new ImageView(heart);
        initHealthText();
        HBox health = new HBox();
        health.getChildren().addAll(heartIcon, healthText);
        health.setAlignment(Pos.CENTER);

        Image moneybag = new Image("/images/view/gui/layouts/info/moneybag.png");
        ImageView moneybagIconView = new ImageView(moneybag);
        initMoneyText();
        HBox money = new HBox();
        money.getChildren().addAll(moneybagIconView, moneyText);
        money.setAlignment(Pos.CENTER);

        ImageView floorImage = GuiHelper.image("/images/map/floor.png", 55, 55);
        initFloorText();
        HBox level = new HBox(floorImage, floorText);
        level.setAlignment(Pos.CENTER);

        getChildren().addAll(playerText, health, money, level);
        setAlignment(Pos.CENTER_LEFT);

        initialize(player);
        player.addPlayerEventListener(new EmptyPlayerEventListener() {

            @Override
            public void onDamageReceived(PlayerDamageEvent event) {
                Animate.pathAnimationBelowTarget(new DamageText(event.getDamageAmount()),
                        health,
                        Direction.DOWN,
                        e -> setHealthText(player.getCurrentHealth(), player.getMaxHealth()));
            }

            @Override
            public void onHealthReceived(PlayerHealthEvent event) {
                Animate.pathAnimationBelowTarget(new HealText(event.getHpAmount()),
                        health,
                        Direction.UP,
                        e -> setHealthText(player.getCurrentHealth(), player.getMaxHealth()));
            }

            @Override
            public void onMaxHealthChanged(PlayerHealthEvent event) {
                Animate.pathAnimationBelowTarget(new HealText(event.getHpAmount()),
                        health,
                        Direction.UP,
                        e -> setHealthText(player.getCurrentHealth(), player.getMaxHealth()));
            }

        });
        player.addInventoryEventListener(new EmptyInventoryEventListener() {

            @Override
            public void onGoldEvent(InventoryEvent event) {
                Animate.pathAnimationBelowTarget(new GoldText((int) event.getValue()),
                        money,
                        event.getDirection() == InventoryEvent.Direction.GAIN ? Direction.UP : Direction.DOWN,
                        e -> setMoneyText(player.getGold()));
            }

            @Override
            public void onLevelEvent(InventoryEvent event) {
                Animate.pathAnimationBelowTarget(new LevelText(),
                        level,
                        Direction.UP,
                        e -> setFloorText(player));
            }

        });
    }

    private void initFloorText() {
        floorText = new StrokedText(FONT_SIZE, Paint.valueOf("#E7D4AD"), Paint.valueOf("#2d2605"), STROKE_WIDTH);
        floorText.setPadding(new Insets(0, 30, 0, 0));
    }

    private void initHealthText() {
        healthText = new StrokedText(FONT_SIZE, Paint.valueOf("#e18b81"), Paint.valueOf("#391a15"), STROKE_WIDTH);
    }

    private void initMoneyText() {
        moneyText = new StrokedText(FONT_SIZE, Paint.valueOf("#e9cd77"), Paint.valueOf("#2d2605"), STROKE_WIDTH);
    }

    private void initPlayerText() {
        playerText = new StrokedText(35, Paint.valueOf("#f3f7f9"), Paint.valueOf("#303739"), STROKE_WIDTH);
    }

    private void initialize(Player player) {
        setPlayerText(player.getName());
        setHealthText(player.getCurrentHealth(), player.getMaxHealth());
        setMoneyText(player.getGold());
        setFloorText(player);
    }

    private void setFloorText(Player player) {
        floorText.setText(player.getCurrentField());
    }

    private void setHealthText(int current, int max) {
        healthText.setText(current + "/" + max);
    }

    private void setMoneyText(int moneyAmount) {
        moneyText.setText(String.valueOf(moneyAmount));
    }

    private void setPlayerText(String playerName) {
        playerText.setText(playerName);
    }

}
