package models.map_elements.field_types;

import controller.BattleViewController;
import controller.LootViewController;
import models.enemy.act_one.Cultist;
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

    public EnemyField(List<Enemy> enemies){
        super("👹");
        this.enemies = enemies;
    }

    @Override
    public void doFieldThing(Player player) {
        if(isFieldBeaten())
            return;

        BattleViewController battle = new BattleViewController(player, enemies);
        battle.startBattle();

        if(!player.isAlive())
            return;

        setFieldBeaten();
        //TODO Es muss geprüft werden, ob das Battle erfolgreich war.
        lootViewController = new LootViewController(player, "EnemyField");
        lootViewController.openLootView(player);

    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }
}
