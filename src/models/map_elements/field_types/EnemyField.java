package models.map_elements.field_types;

import controller.BattleViewController;
import models.enemy.Cultist;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;

public class EnemyField extends Field{

    private List<Enemy> enemies = new ArrayList<>();

    public EnemyField(){
        super("👹");
    }

    @Override
    public void doFieldThing(Player player) {
        List<Enemy> tenemies = new ArrayList<>();
        tenemies.add(new Cultist());

        BattleViewController battle = new BattleViewController(player, tenemies);
        battle.startBattle();
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }
}
