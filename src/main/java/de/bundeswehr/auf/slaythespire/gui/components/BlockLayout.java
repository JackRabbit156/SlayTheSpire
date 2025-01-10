package de.bundeswehr.auf.slaythespire.gui.components;

import de.bundeswehr.auf.slaythespire.gui.components.IconLayout;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
import javafx.scene.control.Tooltip;

/**
 * Das Layout für ein Block Icon.
 *
 * @author L Frank Rieger
 */
public class BlockLayout extends IconLayout {

    public BlockLayout(int amount) {
        if (amount > 0) {
            setText(Integer.toString(amount));
        }
    }

    @Override
    protected double getFontSize() {
        return 30;
    }

    @Override
    protected String getPath() {
        return "/images/view/gui/layouts/battle/Block.png";
    }

    @Override
    protected double getSize() {
        return 60;
    }

    @Override
    protected String getStrokeColor() {
        return "#000000";
    }

    @Override
    protected double getStrokeWidth() {
        return 3;
    }

    @Override
    protected String getTextColor() {
        return "#ffffff";
    }

}
