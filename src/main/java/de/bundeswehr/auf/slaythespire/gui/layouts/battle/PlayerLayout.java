package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import com.sun.javafx.scene.traversal.Direction;
import de.bundeswehr.auf.slaythespire.controller.listener.EmptyPlayerEventListener;
import de.bundeswehr.auf.slaythespire.events.PlayerBlockEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerDamageEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerHealthEvent;
import de.bundeswehr.auf.slaythespire.gui.BattleView;
import de.bundeswehr.auf.slaythespire.gui.components.*;
import de.bundeswehr.auf.slaythespire.helper.Animate;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Das Player layout.
 * Der Player auf der linken Seite
 *
 * @author Warawa Alex
 * @author OF Daniel Willig
 */
public class PlayerLayout extends VBox {

    private IdleAnimation animation;
    private final BattleView battleView;
    private boolean deadFlag = false;
    private final DefendLayout defendLayout;
    private final HealthBarLayout healthBarLayout;
    private final Player player;
    private boolean powerMode = false;
    private boolean skillMode = false;

    public PlayerLayout(Player player, BattleView battleView) {
        this.player = player;
        this.battleView = battleView;

        healthBarLayout = new HealthBarLayout();
        defendLayout = new DefendLayout();

        HBox defendHealthBar = new HBox();
        defendHealthBar.getChildren().addAll(defendLayout, healthBarLayout);
        defendHealthBar.setAlignment(Pos.CENTER);
        defendHealthBar.setTranslateX(40);
        defendHealthBar.setSpacing(-105);

        getChildren().addAll(image(), defendHealthBar);

        setPadding(new Insets(500, 0, 0, 250));
        setAlignment(Pos.BOTTOM_RIGHT);

        updatePlayer();
    }

    public void handlePlayerDeath() {
        animation.stop();
        player.resetListeners();
    }

    private void addCombatText(Node node) {
        player.addPlayerEventListener(new EmptyPlayerEventListener() {

            @Override
            public void onBlockReceived(PlayerBlockEvent event) {
                Animate.pathAnimationAboveTarget(new BlockText(event.getBlockAmount()),
                        node,
                        Direction.UP,
                        e -> updatePlayer());
            }

            @Override
            public void onDamageReceived(PlayerDamageEvent event) {
                Animate.pathAnimationAboveTarget(new DamageText(event.getDamageAmount()),
                        node,
                        Direction.UP,
                        e -> updatePlayer());
            }

            @Override
            public void onHealthReceived(PlayerHealthEvent event) {
                Animate.pathAnimationAboveTarget(new HealText(event.getHpAmount()),
                        node,
                        Direction.UP,
                        e -> updatePlayer());
            }

        });
    }

    private void handlePlayerClick() {
        battleView.clickedOnPlayer();
    }

    private ImageView image() {
        ImageView figure = new PlayerImageView(player);

        addCombatText(figure);

        animation = new MovingAnimation(figure);
        animation.start();

        setHoverEffect(figure);
        figure.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handlePlayerClick());
        battleView.modeProperty().addListener((obs, oldMode, newMode) -> {
            if (newMode == BattleView.Mode.SKILL) {
                skillMode = true;

                DropShadow glowNotSelectedPlayer = new DropShadow();
                glowNotSelectedPlayer.setColor(Color.CYAN);
                glowNotSelectedPlayer.setHeight(30);
                glowNotSelectedPlayer.setWidth(30);

                figure.setEffect(glowNotSelectedPlayer);
                figure.setScaleX(1.0); // Reset the width to original
                figure.setScaleY(1.0); // Reset the height to original
            }
            else if (newMode == BattleView.Mode.POWER) {
                powerMode = true;

                DropShadow glowNotSelectedPlayer = new DropShadow();
                glowNotSelectedPlayer.setColor(Color.CYAN);
                glowNotSelectedPlayer.setHeight(30);
                glowNotSelectedPlayer.setWidth(30);

                figure.setEffect(glowNotSelectedPlayer);
                figure.setScaleX(1.0); // Reset the width to original
                figure.setScaleY(1.0); // Reset the height to original
            }
            else {
                skillMode = false;
                powerMode = false;
                figure.setEffect(null);
            }
        });
        return figure;
    }

    private void setHoverEffect(ImageView imageView) {
        DropShadow glowNotSelectedPlayer = new DropShadow();
        glowNotSelectedPlayer.setColor(Color.CYAN);
        glowNotSelectedPlayer.setHeight(30);
        glowNotSelectedPlayer.setWidth(30);

        DropShadow glowSelectedPlayer = new DropShadow();
        glowSelectedPlayer.setColor(Color.RED);
        glowSelectedPlayer.setHeight(30);
        glowSelectedPlayer.setWidth(30);

        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            if (skillMode || powerMode) {
                imageView.setEffect(glowSelectedPlayer);
                imageView.setScaleX(1.1); // Slightly increase the width
                imageView.setScaleY(1.1); // Slightly increase the height
            }
        });

        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            if (skillMode) {
                imageView.setEffect(glowNotSelectedPlayer);
                imageView.setScaleX(1.0); // Reset the width to original
                imageView.setScaleY(1.0); // Reset the height to original
            }
            else if (powerMode) {
                imageView.setEffect(glowNotSelectedPlayer);
                imageView.setScaleX(1.0); // Reset the width to original
                imageView.setScaleY(1.0); // Reset the height to original
            }
        });
    }

    private void updatePlayer() {
        if (player.getCurrentHealth() <= 0 && !deadFlag) {
            GuiHelper.Scenes.startGameOverScene(player);
            deadFlag = true;
        }
        healthBarLayout.setHealthText(player.getCurrentHealth(), player.getMaxHealth());
        defendLayout.setBlockText(player.getBlock());
    }

}