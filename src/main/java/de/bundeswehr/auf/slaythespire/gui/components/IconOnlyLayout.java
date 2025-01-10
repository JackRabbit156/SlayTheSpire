package de.bundeswehr.auf.slaythespire.gui.components;

import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;

public abstract class IconOnlyLayout extends IconLayout {

    public IconOnlyLayout(String path) {
        super(path);
    }

    @Override
    protected double getFontSize() {
        return 0;
    }

    @Override
    protected String getStrokeColor() {
        return "#000000";
    }

    @Override
    protected double getStrokeWidth() {
        return 0;
    }

    @Override
    protected String getTextColor() {
        return "#000000";
    }

}
