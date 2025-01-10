package de.bundeswehr.auf.slaythespire.gui.components.buttons;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;

public class GlowingButton extends Button {

    public GlowingButton(String imagePath, double width, double height) {
        setGraphic(GuiHelper.image(imagePath, width, height));
        GuiHelper.setHoverEffect(this);
        setBackground(Background.EMPTY);
        setCursor(Cursor.HAND);
    }

}
