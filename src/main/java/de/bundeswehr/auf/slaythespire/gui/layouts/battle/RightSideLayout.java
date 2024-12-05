package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import javafx.scene.layout.HBox;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.gui.BattleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse 'RightSideLayout' repräsentiert das Layout für die
 * rechte Seite der Schlachtansicht (Battle View), in der die
 * feindlichen Gegner angezeigt werden.
 *
 * <p>
 * Diese Klasse verwaltet die Anzeige mehrerer feindlicher Layouts,
 * die jeweils die individuellen Eigenschaften und Statusanzeigen
 * der Feinde umfassen. Sie ermöglicht auch die Aktualisierung
 * der Anzeige, wenn sich der Status der Feinde ändert
 * (z.B. wenn ein Feind besiegt wird).
 * </p>
 *
 * @author Warawa Alexander, Willig Daniel
 */
public class RightSideLayout extends HBox {

    private final List<Enemy> enemies;
    private final List<EnemyLayout> enemyLayout = new ArrayList<>();

    public RightSideLayout(BattleView battleView, List<Enemy> enemies) {
//        setSpacing(-50);
        this.enemies = enemies;
        for (Enemy enemy : enemies) {
            enemyLayout.add(new EnemyLayout(enemy, battleView));
        }
        getChildren().addAll(enemyLayout);
    }

    public void update() {
        for (int i = enemyLayout.size() - 1; i >= 0; i--) {
            enemyLayout.get(i).updateEnemy();
            if (!enemies.get(i).isAlive()) {
                getChildren().remove(i);
                enemyLayout.remove(i);
            }
        }
        enemies.removeIf(enemy -> !enemy.isAlive());
    }

}
