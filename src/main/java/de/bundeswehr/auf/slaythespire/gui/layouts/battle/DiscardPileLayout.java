package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

public class DiscardPileLayout extends IconLayout {

    public DiscardPileLayout() {
        anchorLeft(22.0);
        anchorRight(null);
        anchorTop(null);
        anchorBottom(72.0);
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
        return "/images/view/gui/layouts/battle/DiscardPile.png";
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
