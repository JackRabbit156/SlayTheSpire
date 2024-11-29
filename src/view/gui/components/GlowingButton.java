package view.gui.components;

import helper.GuiHelper;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;

public class GlowingButton extends Button {

    public GlowingButton(String imagePath, double width, double height) {
        setGraphic(GuiHelper.image(imagePath, width, height));
        GuiHelper.setHoverEffect(this);
        setBackground(Background.EMPTY);
    }

}
