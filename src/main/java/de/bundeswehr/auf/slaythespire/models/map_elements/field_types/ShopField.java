package de.bundeswehr.auf.slaythespire.models.map_elements.field_types;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

public class ShopField extends Field{
    private static final String imagePath = "/images/map_elements/field_types/ShopField.png";

    public ShopField() {
        super(imagePath);
    }

    @Override
    public void doFieldThing(Player player) {
        GuiHelper.Scenes.startShopScene(player);
    }
}
