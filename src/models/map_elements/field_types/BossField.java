package models.map_elements.field_types;

import controller.BattleViewController;
import controller.LootViewController;
import models.enemy.Enemy;
import models.enemy.act_one.AcidSlime;
import models.enemy.act_one.bosses.SlimeBoss;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class BossField extends Field{
    private LootViewController lootViewController;
    private Random rand = new Random();
    private List<Enemy> tenemies;
    private int enemyType;

    public BossField() {
        super("\uD83D\uDCAA");
    }

    @Override
    public void doFieldThing(Player player) {
        int currAct = player.getCurrentAct();
        // MUSS 1 Boss
        // KANN 0 - 2 Elite
        // MUSS 0 - 4 Monster + 1 Boss
        int enemyAmount = rand.nextInt(4);

        this.tenemies = new ArrayList<>();
        // ACT BOSSES
        switch (currAct) {
            case 4:
            case 3:
                // The Hexaghost
                // tenemies.add(new Hexaghost());
                // KANN
                break;
            case 2:
                // The Guardian
                // tenemies.add(new TheGuardian());
                // SOLL
                break;
            case 1:
                // Slime Boss
                tenemies.add(createActOneBoss());
                createActOneEnemies(enemyAmount);
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

    private Enemy createActOneBoss() {
        double randDouble = rand.nextDouble();
        if (randDouble < 0.33) {
            this.enemyType = 1; // 1 - SlimBoss
            return new SlimeBoss();
        } else if (randDouble < 0.66) {
            this.enemyType = 1; // 1 - SlimBoss
            return new SlimeBoss();
//            return new TheGuardian();
        } else {
            this.enemyType = 1; // 1 - SlimBoss
            return new SlimeBoss();
//            return new Hexaghost();
        }
    }

    private void createActOneEnemies(int enemyAmount) {

        for (int i = 0; i < enemyAmount; i++) {
            switch (this.enemyType) {
                case 0:
                    this.tenemies.add(new AcidSlime());
                    break;
                case 1:
//                    this.tenemies.add()
                    break;
                case 2:
                    //
//                    this.tenemies.add()
                    break;
            }
        }
    }


}
