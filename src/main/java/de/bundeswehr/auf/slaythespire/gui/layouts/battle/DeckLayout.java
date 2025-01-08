package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

public class DeckLayout extends IconLayout {

    public DeckLayout() {
        anchorRight(18.0);
        anchorLeft(null);
        anchorTop(null);
        anchorBottom(72.0);
    }

    public void setDeckText(int amount) {
        setText("" + amount);
    }

    @Override
    protected double getFontSize() {
        return 35;
    }

    @Override
    protected String getPath() {
        return "/images/view/gui/layouts/battle/Deck.png";
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
    protected double getStrokeWidth() {
        return 7;
    }

    @Override
    protected String getTextColor() {
        return "#FFF9F3";
    }

}
