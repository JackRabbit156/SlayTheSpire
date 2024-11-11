package models.map_elements.field_types;

import controller.cli.BattleViewController;
import controller.cli.LootViewController;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

public class EliteField extends Field {
    private static final String imagePath = "/images/map/boss.png";

    private LootViewController lootViewController;
    private List<Enemy> enemies;

    public EliteField(List<Enemy> enemies) {
        super(imagePath);
        this.enemies = enemies;
    }

    @Override
    public void doFieldThing(Player player) {
        if(isFieldBeaten())
            return;

        BattleViewController battle = new BattleViewController(player, enemies);
        battle.startBattle();

        if(!player.isAlive()) {
            return;
        }

        setFieldBeaten();

        lootViewController = new LootViewController(player, "EliteField");
        lootViewController.openLootView(player);
    }
}
