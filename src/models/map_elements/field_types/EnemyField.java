package models.map_elements.field_types;

import controller.BattleViewController;
import controller.LootViewController;
import models.enemy.Cultist;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;

public class EnemyField extends Field{

    private List<Enemy> enemies = new ArrayList<>();
    private LootViewController lootViewController;
    public EnemyField(){
        super("👹");
    }

    @Override
    public void doFieldThing(Player player) {
        List<Enemy> tenemies = new ArrayList<>();
        tenemies.add(new Cultist());

        BattleViewController battle = new BattleViewController(player, tenemies);
        battle.startBattle();


        //TODO Es muss geprüft werden, ob das Battle erfolgreich war.
        lootViewController = new LootViewController(player, "EnemyField");
        lootViewController.openLootView(player);
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }
}
