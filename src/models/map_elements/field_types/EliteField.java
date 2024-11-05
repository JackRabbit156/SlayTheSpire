package models.map_elements.field_types;

import controller.LootViewController;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

public class EliteField extends Field {
    private LootViewController lootViewController;
    private List<Enemy> enemies;

    public EliteField(List<Enemy> enemies) {
        super("\uD83D\uDCA2");
        this.enemies = enemies;
    }

    @Override
    public void doFieldThing(Player player) {
        if(isFieldBeaten())
            return;

        if(!player.isAlive()) {
            return;
        }

        setFieldBeaten();

        lootViewController = new LootViewController(player, "EliteField");
        lootViewController.openLootView(player);
    }
}
