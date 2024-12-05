package de.bundeswehr.auf.slaythespire.model.map.field;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * @author Keil, Vladislav
 */
public class TreasureField extends Field {
    private static final String imagePath = "/images/map/field_types/TreasureField.png";
    public TreasureField() {
        super(imagePath);
    }

    @Override
    public void doFieldThing(Player player) {
        GuiHelper.Scenes.startTreasureScene(player);
    }
}
