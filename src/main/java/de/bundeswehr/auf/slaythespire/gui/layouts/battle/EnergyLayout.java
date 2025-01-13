package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import de.bundeswehr.auf.slaythespire.gui.components.IconLayout;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Das Energy layout.
 * Das kleine Icon links, welches angibt wie viel energy der spieler noch hat
 *
 * @author OF Daniel Willig
 */
public class EnergyLayout extends IconLayout {

    private final Player player;

    public EnergyLayout(Player player) {
        super(player.getEnergyIconPath());
        this.player = player;
    }

    public void setEnergyText(int currentEnergy, int maxEnergy) {
        setText(currentEnergy + "/" + maxEnergy);
    }

    @Override
    protected double getFontSize() {
        return 35;
    }

    @Override
    protected String getPath() {
        return player.getEnergyIconPath();
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
