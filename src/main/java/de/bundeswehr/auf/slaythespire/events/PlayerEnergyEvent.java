package de.bundeswehr.auf.slaythespire.events;

import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Das Player Energie Event
 * @author OF Daniel Willig
 */
public class PlayerEnergyEvent {

    private final Player player;
    private final int energyAmount;

    /**
     * Constructor PlayerEnergyEvent
     *
     * @param player      der Spieler
     * @param energyAmount Energie Menge
     */
    public PlayerEnergyEvent(Player player, int energyAmount) {
        this.player = player;
        this.energyAmount = energyAmount;
    }

    /**
     * getter PlayerEvent
     *
     * @return das Spieler Event
     */
    public Player getPlayerEvent() {
        return player;
    }

    /**
     * getter BlockAmount
     *
     * @return die Block Menge
     */
    public int getEnergyAmount() {
        return energyAmount;
    }

}
