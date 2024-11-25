package view.gui.layouts.battle_view_layouts;

import javafx.scene.layout.HBox;
import models.enemy.Enemy;
import view.gui.BattleView;
import view.gui.layouts.battle_view_layouts.EnemyLayout;

import java.util.ArrayList;
import java.util.List;

public class RightSideLayout extends HBox {
    private List<EnemyLayout> enemyLayout = new ArrayList<>();
    private List<Enemy> enemies;
    private BattleView battleView;

    public RightSideLayout(BattleView battleView, List<Enemy> enemies) {
        setSpacing(-50);
        this.battleView = battleView;
        this.enemies = enemies;

        for (Enemy enemy : enemies) {
            enemyLayout.add(new EnemyLayout(enemy, battleView));
        }

        addEnemiesToLayout();
    }

    private void addEnemiesToLayout(){
        getChildren().addAll(enemyLayout);
    }

    public void refreshRightSide(){
        for(int i = enemyLayout.size() - 1; i >= 0; i--){
            enemyLayout.get(i).updateEnemy();
            if (!enemies.get(i).isAlive()) {
                getChildren().remove(i);
                enemyLayout.remove(i);
            }
        }
        enemies.removeIf(enemy -> !enemy.isAlive());
    }
}
