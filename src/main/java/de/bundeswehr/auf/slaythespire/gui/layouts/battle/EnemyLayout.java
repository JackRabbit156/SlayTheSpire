package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import com.sun.javafx.scene.traversal.Direction;
import de.bundeswehr.auf.slaythespire.controller.listener.EmptyEnemyEventListener;
import de.bundeswehr.auf.slaythespire.events.EffectEvent;
import de.bundeswehr.auf.slaythespire.events.EnemyBlockEvent;
import de.bundeswehr.auf.slaythespire.events.EnemyDamageEvent;
import de.bundeswehr.auf.slaythespire.gui.BattleView;
import de.bundeswehr.auf.slaythespire.gui.components.*;
import de.bundeswehr.auf.slaythespire.gui.components.animation.BlockIconLayout;
import de.bundeswehr.auf.slaythespire.gui.components.animation.DamageText;
import de.bundeswehr.auf.slaythespire.gui.components.animation.IdleAnimation;
import de.bundeswehr.auf.slaythespire.gui.components.animation.MovingAnimation;
import de.bundeswehr.auf.slaythespire.helper.Animate;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
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

    private IdleAnimation animation;
    private boolean attackMode = false;
    private final BattleView battleView;
    private final DefendLayout defendLayout;
    private final EffectBarLayout effectBarLayout;
    private final Enemy enemy;
    private final HealthBarLayout healthBarLayout;
    private final IntentLayout intentLayout;

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
        effectBarLayout = new EffectBarLayout(enemy);

        HBox defendHealthBar = new HBox();
        defendHealthBar.getChildren().addAll(defendLayout, healthBarLayout);
        defendHealthBar.setAlignment(Pos.CENTER);
        defendHealthBar.setTranslateX(-25);
        defendHealthBar.setSpacing(-105);

        getChildren().addAll(intentLayout, image(), defendHealthBar, effectBarLayout);

        setMargin(healthBarLayout, new Insets(0, 100, 0, 0));

        alignmentProperty().set(Pos.BOTTOM_LEFT);
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void handleEnemyDeath() {
        animation.stop();
        enemy.resetListeners();
    }

    /**
     * Aktualisiert das Layout des Feindes mit den aktuellen
     * Gesundheitswerten, Verteidigungswerten und Absichten.
     */
    public void updateEnemy() {
        intentLayout.setIntentText(enemy.getIntent().getIconText());
        intentLayout.setIntentIcon(enemy.getIntent().getImagePath());
        effectBarLayout.update();
    }

    private void addCombatText(Node node) {
        defendLayout.setBlockText(enemy.getBlock());
        healthBarLayout.setHealthText(enemy.getCurrentHealth(), enemy.getMaxHealth());
        enemy.addEnemyEventListener(new EmptyEnemyEventListener() {

            @Override
            public void onBlockReceived(EnemyBlockEvent event) {
                Animate.pathAnimationAboveTarget(new BlockIconLayout(event.getBlockAmount()),
                        node,
                        Direction.UP,
                        e -> defendLayout.setBlockText(enemy.getBlock()));
            }

            @Override
            public void onDamageReceived(EnemyDamageEvent event) {
                if (event.getDamageAmount() == 0) {
                    Animate.shatterAnimation(new BlockIconLayout(0),
                            node,
                            e -> defendLayout.setBlockText(enemy.getBlock()));
                }
                else {
                    Animate.pathAnimationAboveTarget(new DamageText(event.getDamageAmount()),
                            node,
                            Direction.UP,
                            e -> {
                                healthBarLayout.setHealthText(enemy.getCurrentHealth(), enemy.getMaxHealth());
                                defendLayout.setBlockText(enemy.getBlock());
                    });
                }
            }

            @Override
            public void onEffect(EffectEvent event) {
                Animate.pathAnimationBelowTarget(new EffectLayout(event.getEffect(), event.getValue()),
                        node,
                        Direction.DOWN,
                        e -> effectBarLayout.update());
            }

        });
    }

    /**
     * Verarbeitet das Klicken auf den Feind.
     */
    private void handleEnemyClick() {
        battleView.clickedOnEnemy(enemy);
    }

    private ImageView image() {
        ImageView figure = new EnemyImageView(enemy);

        addCombatText(figure);

        animation = new MovingAnimation(figure);
        animation.start();

        setHoverEffect(figure);
        figure.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handleEnemyClick());
        battleView.modeProperty().addListener((obs, oldMode, newMode) -> {
            if (newMode == BattleView.Mode.ATTACK) {
                attackMode = true;

                DropShadow glowNotSelectedEnemy = new DropShadow();
                glowNotSelectedEnemy.setColor(Color.CYAN);
                glowNotSelectedEnemy.setHeight(30);
                glowNotSelectedEnemy.setWidth(30);

                figure.setEffect(glowNotSelectedEnemy);
                figure.setScaleX(1.0); // Reset the width to original
                figure.setScaleY(1.0); // Reset the height to original
            }
            else {
                attackMode = false;
                figure.setEffect(null);
            }
        });
        return figure;
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
