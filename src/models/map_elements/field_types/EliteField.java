package models.map_elements.field_types;

import controller.gui.LootController;
import helper.GuiHelper;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

public class EliteField extends Field {
    private static final String imagePath = "/images/map_elements/field_types/EliteField.png";

    private LootController lootController;
    private List<Enemy> enemies;

    public EliteField(List<Enemy> enemies) {
        super(imagePath);
        this.enemies = enemies;
    }

    @Override
    public void doFieldThing(Player player) {
        if(isFieldBeaten())
            return;

        GuiHelper.Scenes.startBattleScene(player, enemies, FieldEnum.ELITEFIELD);

        if(!player.isAlive()) {
            return;
        }

        setFieldBeaten();
    }
}
