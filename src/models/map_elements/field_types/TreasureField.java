package models.map_elements.field_types;

import helper.GuiHelper;
import models.player.player_structure.Player;

/**
 * @author Keil, Vladislav
 */
public class TreasureField extends Field {
    private static final String imagePath = "/images/map_elements/field_types/TreasureField.png";
    public TreasureField() {
        super(imagePath);
    }

    @Override
    public void doFieldThing(Player player) {
        GuiHelper.Scenes.startTreasureScene(player);
    }
}
