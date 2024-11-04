package models.map_elements.field_types;

import controller.BattleViewController;
import controller.LootViewController;
import models.enemy.Enemy;
import models.enemy.act_one.AcidSlime;
import models.enemy.act_one.bosses.SlimeBoss;
import models.player.player_structure.Player;
import view.StatisticsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class BossField extends Field{
    private LootViewController lootViewController;
    private List<Enemy> enemies;
    private StatisticsView statisticsView;

    public BossField(List<Enemy> enemies) {
        super("\uD83D\uDCAA");
        this.enemies = enemies;
    }

    @Override
    public void doFieldThing(Player player) {
        if(isFieldBeaten())
            return;

        this.enemies = new ArrayList<>();

        BattleViewController battle = new BattleViewController(player, enemies);
        battle.startBattle();

        if(!player.isAlive()) {
            return;
        }

        setFieldBeaten();

        statisticsView = new StatisticsView();
        statisticsView.display(player);

        lootViewController = new LootViewController(player, "BossField");
        lootViewController.openLootView(player);
    }
}
