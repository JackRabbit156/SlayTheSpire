package models.map_elements.field_types;

import controller.gui.ShopController;
import helper.GuiHelper;
import models.player.player_structure.Player;

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
