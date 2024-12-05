package de.bundeswehr.auf.slaythespire.models.map_elements.field_types;

import de.bundeswehr.auf.slaythespire.controller.gui.LootController;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.helper.MusicBoy;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

import java.util.List;

public class EliteField extends Field {
    private static final String imagePath = "/images/map_elements/field_types/EliteField.png";

    private LootController lootController;
    private List<Enemy> enemies;

    public EliteField(List<Enemy> enemies) {
        super(imagePath);
        MusicBoy.play("elite");
        this.enemies = enemies;
    }

    @Override
    public void doFieldThing(Player player) {
        GuiHelper.Scenes.startBattleScene(player, enemies, FieldEnum.ELITEFIELD);
    }
}
