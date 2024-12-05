package de.bundeswehr.auf.slaythespire.model.map.field;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.helper.MusicBoy;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;

/**
 * @author Keil, Vladislav
 */
public class BossField extends Field{
    private static final String imagePath = "/images/map/field_types/BossField.png";

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
