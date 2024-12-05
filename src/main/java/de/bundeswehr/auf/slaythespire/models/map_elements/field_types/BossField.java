package de.bundeswehr.auf.slaythespire.models.map_elements.field_types;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.helper.MusicBoy;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

import java.util.List;

/**
 * @author Keil, Vladislav
 */
public class BossField extends Field{
    private static final String imagePath = "/images/map_elements/field_types/BossField.png";

    private List<Enemy> enemies;

    public BossField(List<Enemy> enemies) {
        super(imagePath);
        MusicBoy.play("boss");
        this.enemies = enemies;
    }

    @Override
    public void doFieldThing(Player player) {
        GuiHelper.Scenes.startBattleScene(player, enemies, FieldEnum.BOSSFIELD);
    }
}
