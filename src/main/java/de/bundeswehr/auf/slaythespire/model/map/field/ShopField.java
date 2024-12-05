package de.bundeswehr.auf.slaythespire.model.map.field;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

public class ShopField extends Field{
    private static final String imagePath = "/images/map/field_types/ShopField.png";

    public ShopField() {
        super(imagePath);
    }

    @Override
    public void doFieldThing(Player player) {
        GuiHelper.Scenes.startShopScene(player);
    }
}
