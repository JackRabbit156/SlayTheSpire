package de.bundeswehr.auf.slaythespire.gui.components;

import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;

public class RelicIconLayout extends IconOnlyLayout {

    private final Relic relic;

    public RelicIconLayout(Relic relic) {
        super(relic.getImagePath());
        this.relic = relic;
    }

    @Override
    protected String getPath() {
        return relic.getImagePath();
    }

    @Override
    protected double getSize() {
        return 30;
    }

}
