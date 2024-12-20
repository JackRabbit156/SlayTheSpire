package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import de.bundeswehr.auf.slaythespire.gui.components.MovingAnimation;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.gui.BattleView;

/**
 * Das Player layout.
 * Der Player auf der linken Seite
 *
 * @author Warawa Alex
 * @author OF Daniel Willig
 */
public class PlayerLayout extends VBox {

    private final Player player;
    private final HealthBarLayout healthBarLayout;
    private final DefendLayout defendLayout;
    private MovingAnimation animation;
    private final BattleView battleView;
    private boolean skillMode = false;
    private boolean powerMode = false;
    private boolean deadFlag = false;

    public PlayerLayout(Player player, BattleView battleView){
        this.player = player;
        this.battleView = battleView;

        healthBarLayout = new HealthBarLayout();
        defendLayout = new DefendLayout();

        HBox defendHealthBar = new HBox();
        defendHealthBar.getChildren().addAll(defendLayout, healthBarLayout);
        defendHealthBar.setAlignment(Pos.CENTER);
        defendHealthBar.setTranslateX(40);
        defendHealthBar.setSpacing(-105);

        this.getChildren().addAll(image(), defendHealthBar);

        this.setPadding(new Insets(500, 0, 0, 450));
        this.alignmentProperty().set(Pos.BOTTOM_RIGHT);

        updatePlayer();
    }

    public void handlePlayerDeath() {
        animation.stop();
    }

    public void updatePlayer(){
        if (player.getCurrentHealth() <= 0 && !deadFlag) {
            GuiHelper.Scenes.startGameOverScene(player);
            deadFlag = true;
        }
        healthBarLayout.setHealthText(player.getCurrentHealth(), player.getMaxHealth());
        defendLayout.setBlockText(player.getBlock());
    }

    private ImageView image() {
        ImageView figure = new ImageView(new Image(player.getImagePath()));
        figure.setPreserveRatio(true);
        figure.setStyle("-fx-background-color: #926099;");

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
            } else if (newMode == BattleView.Mode.POWER) {
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

    private void setHoverEffect (ImageView imageView) {
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

    private void handlePlayerClick() {
        battleView.clickedOnPlayer();
    }

}