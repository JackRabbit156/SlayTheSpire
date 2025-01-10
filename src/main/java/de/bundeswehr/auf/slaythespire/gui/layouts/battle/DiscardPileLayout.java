package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import de.bundeswehr.auf.slaythespire.gui.components.IconLayout;

public class DiscardPileLayout extends IconLayout {

    public DiscardPileLayout() {
        anchorLeft(22.0);
        anchorRight(null);
        anchorTop(null);
        anchorBottom(77.0);
    }

    public void setDiscardPileText(int amount) {
        setText("" + amount);
    }

    @Override
    protected double getFontSize() {
        return 35;
    }

    @Override
    protected String getPath() {
        return "/images/gui/battle/DiscardPile.png";
    }

    @Override
    protected double getSize() {
        return 120;
    }

    @Override
    protected String getStrokeColor() {
        return "#632411";
    }

    @Override
    protected String getTextColor() {
        return "#FFF9F3";
    }

    @Override
    protected double getStrokeWidth() {
        return 7;
    }

}
