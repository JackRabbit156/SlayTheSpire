package view.gui.layouts.battle_view_layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import models.enemy.Enemy;
import view.gui.BattleView;

public class EnemyLayout extends VBox {
    private Enemy enemy;
    private HealthBarLayout healthBarLayout;
    private DefendLayout defendLayout;
    private MovingAnimation animation;
    private BattleView battleView;
    private boolean attackMode = false;

    public EnemyLayout(Enemy enemy, BattleView battleView){
        this.enemy = enemy;
        this.battleView = battleView;
        healthBarLayout = new HealthBarLayout(100);
        defendLayout = new DefendLayout();

        HBox defendHealthBar = new HBox();
        defendHealthBar.getChildren().addAll(defendLayout, healthBarLayout);
        defendHealthBar.setAlignment(Pos.CENTER);
        defendHealthBar.setTranslateX(-25);
        defendHealthBar.setSpacing(-105);

        this.getChildren().addAll(image(), defendHealthBar);

        // Die HP Bar muss nachjustiert werden
        setMargin(healthBarLayout, new Insets(0, 100, 0, 0));

        this.alignmentProperty().set(Pos.BOTTOM_LEFT);

        updateEnemy();

        animation = new MovingAnimation(this);
        animation.start();
    }

    public void updateEnemy() {
        healthBarLayout.setHealthText(enemy.getHealth(), enemy.getMaxHealth());
    }

    private ImageView image() {
        Image figureImage = new Image(getClass().getResource(enemy.getImagePath()).toExternalForm());
        ImageView imageViewFigure = new ImageView(figureImage);

        imageViewFigure.setFitWidth(350); // Breite in Pixel
        imageViewFigure.setFitHeight(350); // Höhe in Pixel
        imageViewFigure.setPreserveRatio(true);
        //handBox = new Pane( imageViewIronclad);
        imageViewFigure.setStyle("-fx-background-color: #926099;");

        setHoverEffect(imageViewFigure);

        imageViewFigure.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            handleEnemyClick(enemy); // Hier eine Methode aufrufen, die das Klick-Event verarbeitet
        });

        battleView.modeProperty().addListener((obs, oldMode, newMode) -> {
            if (newMode == BattleView.Mode.ATTACK) {
                attackMode = true;

                DropShadow glowNotSelectedEnemy = new DropShadow();
                glowNotSelectedEnemy.setColor(Color.CYAN);
                glowNotSelectedEnemy.setHeight(30);
                glowNotSelectedEnemy.setWidth(30);

                imageViewFigure.setEffect(glowNotSelectedEnemy);
                imageViewFigure.setScaleX(1.0); // Reset the width to original
                imageViewFigure.setScaleY(1.0); // Reset the height to original
            } else {
                attackMode = false;
                imageViewFigure.setEffect(null);
            }
        });

        return imageViewFigure;
    }

    private void setHoverEffect (ImageView imageView) {
        DropShadow glowNotSelectedEnemy = new DropShadow();
        glowNotSelectedEnemy.setColor(Color.CYAN);
        glowNotSelectedEnemy.setHeight(30);
        glowNotSelectedEnemy.setWidth(30);

        DropShadow glowSelectedEnemy = new DropShadow();
        glowSelectedEnemy.setColor(Color.RED);
        glowSelectedEnemy.setHeight(30);
        glowSelectedEnemy.setWidth(30);

        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            if (attackMode) {
                imageView.setEffect(glowSelectedEnemy);
                imageView.setScaleX(1.1); // Slightly increase the width
                imageView.setScaleY(1.1); // Slightly increase the height
            }
        });

        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            if (attackMode) {
                imageView.setEffect(glowNotSelectedEnemy);
                imageView.setScaleX(1.0); // Reset the width to original
                imageView.setScaleY(1.0); // Reset the height to original
            }
        });
    }

    public void handleEnemyClick(Enemy enemy) {
        // Verarbeite hier den Klick auf die Karte, z.B. öffne Details oder führe eine Aktion aus
        battleView.clickedOnEnemy(enemy);
    }
}
