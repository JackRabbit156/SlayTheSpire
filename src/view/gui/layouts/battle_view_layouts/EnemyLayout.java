package view.gui.layouts.battle_view_layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import models.enemy.Enemy;
import view.gui.BattleView;

public class EnemyLayout extends VBox {
    private Enemy enemy;
    private HealthBarLayout healthBarLayout;
    private MovingAnimation animation;

    private BattleView battleView;

    public EnemyLayout(Enemy enemy, BattleView battleView){
        this.enemy = enemy;
        this.battleView = battleView;
        healthBarLayout = new HealthBarLayout(100);

        this.getChildren().addAll(image(), healthBarLayout);

        // Moving the healthbar to the left
        setMargin(healthBarLayout, new Insets(0, 100, 0, 0));

        this.setPadding(new Insets(190, 200, 0, 0));
        this.alignmentProperty().set(Pos.CENTER_LEFT);

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

        imageViewFigure.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            handleEnemyClick(enemy); // Hier eine Methode aufrufen, die das Klick-Event verarbeitet
        });

        return imageViewFigure;
    }
    public void handleEnemyClick(Enemy enemy) {
        // Verarbeite hier den Klick auf die Karte, z.B. öffne Details oder führe eine Aktion aus
        battleView.clickedOnEnemy(enemy);
    }
}
