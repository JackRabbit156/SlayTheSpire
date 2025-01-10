package de.bundeswehr.auf.slaythespire.gui.components.animation;

import de.bundeswehr.auf.slaythespire.gui.components.IconOnlyLayout;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;

public class PotionIconLayout extends IconOnlyLayout {

    private final Potion potion;

    public PotionIconLayout(Potion potion) {
        super(potion.getImagePath());
        this.potion = potion;
    }

    @Override
    protected String getPath() {
        return potion.getImagePath();
    }

    @Override
    protected double getSize() {
        return 30;
    }

}
