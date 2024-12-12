package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import de.bundeswehr.auf.slaythespire.gui.BattleView;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Die Klasse 'EnemyLayout' repräsentiert das Layout für einen
 * Feind im Schlachtansicht (Battle View). Sie zeigt die
 * Gesundheitsleiste des Feindes, seine Verteidigungswerte und
 * die Absicht des Feindes an.
 *
 * <p>
 * Diese Klasse verwaltet auch die visuelle Darstellung des Feindes
 * und ermöglicht Interaktionen wie das Klicken auf die feindliche
 * Figur. Mit Hover-Effekten werden visuelle Hinweise gegeben,
 * ob der Spieler angreifen kann.
 * </p>
 *
 * @author Warawa Alexander, Willig Daniel
 */
public class EnemyLayout extends VBox {
    private MovingAnimation animation;
    private boolean attackMode = false;
    private BattleView battleView;
    private DefendLayout defendLayout;
    private Enemy enemy;
    private HealthBarLayout healthBarLayout;
    private IntentLayout intentLayout;


    /**
     * Konstruktor für die Klasse 'EnemyLayout'.
     *
     * <p>
     * Dieser Konstruktor initialisiert das Layout mit dem angegebenen
     * Feind und der Schlachtansicht. Es werden die Gesundheitsleiste,
     * der Verteidigungswert und die Absicht des Feindes angezeigt.
     * </p>
     *
     * @param enemy      Der Feind, der angezeigt werden soll
     * @param battleView Die aktuelle Instanz der Schlachtansicht
     */
    public EnemyLayout(Enemy enemy, BattleView battleView) {
        this.enemy = enemy;
        this.battleView = battleView;
        healthBarLayout = new HealthBarLayout();
        defendLayout = new DefendLayout();
        intentLayout = new IntentLayout(enemy);

//        intentLayout.setTranslateY(50);

        HBox defendHealthBar = new HBox();
        defendHealthBar.getChildren().addAll(defendLayout, healthBarLayout);
        defendHealthBar.setAlignment(Pos.CENTER);
        defendHealthBar.setTranslateX(-25);
        defendHealthBar.setSpacing(-105);

        getChildren().addAll(intentLayout, image(), defendHealthBar);

        // Die HP Bar muss nachjustiert werden
        setMargin(healthBarLayout, new Insets(0, 100, 0, 0));

        alignmentProperty().set(Pos.BOTTOM_LEFT);

        updateEnemy();

        animation = new MovingAnimation(this);
        animation.start();
    }

    public void handleEnemyDeath() {
        animation.stop();
    }

    /**
     * Aktualisiert das Layout des Feindes mit den aktuellen
     * Gesundheitswerten, Verteidigungswerten und Absichten.
     */
    public void updateEnemy() {
        healthBarLayout.setHealthText(enemy.getHealth(), enemy.getMaxHealth());
        defendLayout.setBlockText(enemy.getBlock());
        intentLayout.setIntentText(enemy.getIntent().getIconText());
        intentLayout.setIntentIcon(enemy.getIntent().getImagePath());
    }

    /**
     * Verarbeitet das Klicken auf den Feind.
     */
    private void handleEnemyClick() {
        // Verarbeite hier den Klick auf die Karte, z.B. öffne Details oder führe eine Aktion aus
        battleView.clickedOnEnemy(enemy);
    }

    private ImageView image() {
        Image figureImage = new Image(getClass().getResource(enemy.getImagePath()).toExternalForm());
        ImageView imageViewFigure = new ImageView(figureImage);

        // TODO better size for bosses and elites
        imageViewFigure.setFitWidth(Math.sqrt(imageViewFigure.getImage().getWidth()) * 10); // Breite in Pixel
        imageViewFigure.setFitHeight(Math.sqrt(imageViewFigure.getImage().getHeight()) * 10); // Höhe in Pixel
        imageViewFigure.setPreserveRatio(true);
        //handBox = new Pane( imageViewIronclad);
        imageViewFigure.setStyle("-fx-background-color: #926099;");

        setHoverEffect(imageViewFigure);

        imageViewFigure.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handleEnemyClick());

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
            }
            else {
                attackMode = false;
                imageViewFigure.setEffect(null);
            }
        });

        return imageViewFigure;
    }

    private void setHoverEffect(ImageView imageView) {
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

}
