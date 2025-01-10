package de.bundeswehr.auf.slaythespire.gui.components.animation;

import de.bundeswehr.auf.slaythespire.gui.components.IconLayout;

/**
 * Das Layout für ein Block Icon.
 *
 * @author L Frank Rieger
 */
public class BlockIconLayout extends IconLayout {

    public BlockIconLayout(int amount) {
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
        return "/images/gui/battle/Block.png";
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
