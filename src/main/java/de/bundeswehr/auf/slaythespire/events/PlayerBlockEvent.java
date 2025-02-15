package de.bundeswehr.auf.slaythespire.events;

import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Das Player Block Event
 * @author OF Daniel Willig
 */
public class PlayerBlockEvent {

    private final Player player;
    private final int blockAmount;

    /**
     * Constructor PlayerBlockEvent
     *
     * @param player      der Spieler
     * @param blockAmount Block Menge
     */
    public PlayerBlockEvent(Player player, int blockAmount) {
        this.player = player;
        this.blockAmount = blockAmount;
    }

    /**
     * getter PlayerEvent
     *
     * @return das Spieler Event
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * getter BlockAmount
     *
     * @return die Block Menge
     */
    public int getBlockAmount() {
        return blockAmount;
    }

}
