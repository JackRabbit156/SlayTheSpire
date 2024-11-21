package models.map_elements.field_types;

import controller.cli.BattleViewController;
import controller.cli.LootViewController;
import controller.gui.LootController;
import helper.GuiHelper;
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

        GuiHelper.Scenes.startBattleScene(player, enemies);

        if(!player.isAlive()) {
            return;
        }

        setFieldBeaten();
        // TODO Alle Szenen starten beim aufruf
//        GuiHelper.Scenes.startStatisticScene(player);

//        GuiHelper.Scenes.startLootScene(player, FieldEnum.BOSSFIELD);
    }
}
