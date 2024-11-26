package models.map_elements.field_types;

import controller.gui.LootController;
import helper.GuiHelper;
import models.enemy.Enemy;
import models.player.player_structure.Player;
import view.gui.StatisticView;

import java.util.List;

/**
 * @author Keil, Vladislav
 */
public class BossField extends Field{
    private static final String imagePath = "/images/map_elements/field_types/BossField.png";

    private List<Enemy> enemies;

    public BossField(List<Enemy> enemies) {
        super(imagePath);
        this.enemies = enemies;
    }

    @Override
    public void doFieldThing(Player player) {
        GuiHelper.Scenes.startBattleScene(player, enemies, FieldEnum.BOSSFIELD);
    }
}
