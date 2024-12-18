package de.bundeswehr.auf.slaythespire.events;

import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Das Player Lebenskraft Event
 * @author OF Daniel Willig
 */
public class PlayerHealthEvent {
    
    private final Player player;
    private final int hpAmount;

    /**
     * Constructor PlayerHealthEvent
     *
     * @param player     der Spieler
     * @param hpAmount   Lebenskraft Menge
     */
    public PlayerHealthEvent(Player player, int hpAmount) {
        this.player = player;
        this.hpAmount = hpAmount;
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
    public int getHpAmount() {
        return hpAmount;
    }

}
