package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

/**
 * Das Energy layout.
 * Das kleine Icon links, welches angibt wie viel energy der spieler noch hat
 *
 * @author OF Daniel Willig
 */
public class EnergyLayout extends IconLayout {

    public void setEnergyText(int currentEnergy, int maxEnergy) {
        String text = currentEnergy + "/" + maxEnergy;
        setText(text);
    }

    @Override
    protected double getFontSize() {
        return 35;
    }

    @Override
    protected String getPath() {
        return "/images/view/gui/layouts/battle/Energy.png";
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
        return "#fffbe6";
    }

}
