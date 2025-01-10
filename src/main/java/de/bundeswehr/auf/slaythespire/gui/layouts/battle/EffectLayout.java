package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import de.bundeswehr.auf.slaythespire.gui.components.IconLayout;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
import javafx.scene.control.Tooltip;

/**
 * Das Layout für ein Effect Icon.
 *
 * @author L Frank Rieger
 */
public class EffectLayout extends IconLayout {

    private final Effect effect;
    private final int value;

    public EffectLayout(Effect effect, int value) {
        super(effect.getImagePath());
        this.effect = effect;
        this.value = value;
        if (value > 1) {
            setText(Integer.toString(value));
        }
        Tooltip.install(this, new Tooltip(effect.getDescription()));
    }

    @Override
    protected double getFontSize() {
        return 15;
    }

    @Override
    protected String getPath() {
        return effect.getImagePath();
    }

    @Override
    protected double getSize() {
        return 30;
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
