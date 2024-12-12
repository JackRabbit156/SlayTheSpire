package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

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
    private final MovingAnimation animation;
    private final BattleView battleView;
    private boolean skillMode = false;
    private boolean powerMode = false;
    private boolean deadFlag = false;

    private String imagePath;

    public PlayerLayout(Player player, BattleView battleView){
        this.player = player;
        this.battleView = battleView;

        this.imagePath = player.getImagePath();
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

        animation = new MovingAnimation(this);
        animation.start();
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
        Image figureImage = new Image(getClass().getResource(player.getImagePath()).toExternalForm());
        ImageView imageViewFigure = new ImageView(figureImage);

//        imageViewFigure.setFitWidth(350); // Breite in Pixel
//        imageViewFigure.setFitHeight(350); // Höhe in Pixel
        imageViewFigure.setPreserveRatio(true);
        //handBox = new Pane( imageViewIronclad);
        imageViewFigure.setStyle("-fx-background-color: #926099;");

        setHoverEffect(imageViewFigure);

        imageViewFigure.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            handlePlayerClick(); // Hier eine Methode aufrufen, die das Klick-Event verarbeitet
        });

        battleView.modeProperty().addListener((obs, oldMode, newMode) -> {
            if (newMode == BattleView.Mode.SKILL) {
                skillMode = true;

                DropShadow glowNotSelectedPlayer = new DropShadow();
                glowNotSelectedPlayer.setColor(Color.CYAN);
                glowNotSelectedPlayer.setHeight(30);
                glowNotSelectedPlayer.setWidth(30);

                imageViewFigure.setEffect(glowNotSelectedPlayer);
                imageViewFigure.setScaleX(1.0); // Reset the width to original
                imageViewFigure.setScaleY(1.0); // Reset the height to original
            } else if (newMode == BattleView.Mode.POWER) {
                powerMode = true;

                DropShadow glowNotSelectedPlayer = new DropShadow();
                glowNotSelectedPlayer.setColor(Color.CYAN);
                glowNotSelectedPlayer.setHeight(30);
                glowNotSelectedPlayer.setWidth(30);

                imageViewFigure.setEffect(glowNotSelectedPlayer);
                imageViewFigure.setScaleX(1.0); // Reset the width to original
                imageViewFigure.setScaleY(1.0); // Reset the height to original
            }

            else {
                skillMode = false;
                powerMode = false;
                imageViewFigure.setEffect(null);
            }
        });









        return imageViewFigure;
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
            if (skillMode) {
                imageView.setEffect(glowSelectedPlayer);
                imageView.setScaleX(1.1); // Slightly increase the width
                imageView.setScaleY(1.1); // Slightly increase the height
            }
            else if (powerMode) {
                imageView.setEffect(glowSelectedPlayer);
                imageView.setScaleY(1.1); // Slightly increase the height
                imageView.setScaleX(1.1); // Slightly increase the width
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
                imageView.setScaleY(1.0); // Reset the height to original
                imageView.setScaleX(1.0); // Reset the width to original
            }
        });
    }

    public void handlePlayerClick() {
        // Verarbeite hier den Klick auf die Karte, z.B. öffne Details oder führe eine Aktion aus
        battleView.clickedOnPlayer();
    }
}