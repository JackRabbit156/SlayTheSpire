package models.map_elements.field_types;

import controller.cli.BattleViewController;
import controller.cli.LootViewController;
import models.enemy.Enemy;
import models.player.player_structure.Player;
import view.cli.StatisticsView;
import view.gui.StatisticView;

import java.util.List;

/**
 * @author Keil, Vladislav
 */
public class BossField extends Field{
    private static final String imagePath = "/images/map_elements/field_types/BossField.png";

    private LootViewController lootViewController;
    private List<Enemy> enemies;

    public BossField(List<Enemy> enemies) {
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

        new StatisticView(player);

        lootViewController = new LootViewController(player, "BossField");
        lootViewController.openLootView(player);
    }
}
