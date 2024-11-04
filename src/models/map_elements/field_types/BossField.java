package models.map_elements.field_types;

import controller.BattleViewController;
import controller.LootViewController;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BossField extends Field{
    private LootViewController lootViewController;
    private Random rand = new Random();
    private List<Enemy> tenemies;
    Enemy boss;
    
    public BossField() {
        super("\uD83D\uDCAA");
    }

    @Override
    public void doFieldThing(Player player) {
        // KANN 0 - 2 Elite
        // MUSS 0 - 4 Monster + 1 Boss
        int enemyAmount = rand.nextInt(4);
        int enemyType = rand.nextInt(3);

        switch (enemyType) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }

        // Return Value ob man gewonnen hat, sonst switcht er ins Lootmenü
        // Statistik ausgabe nach einem Boss Fight
        BattleViewController battle = new BattleViewController(player, tenemies);
        battle.startBattle();

        //TODO Add here LootViewController(player, FieldType)
        lootViewController = new LootViewController(player, "BossField");
        lootViewController.openLootView(player);
    }


    private void createEnemies(int enemyAmount, int enemyType) {
        for (int i = 0; i < enemyAmount; i++) {

        }
    }
}
