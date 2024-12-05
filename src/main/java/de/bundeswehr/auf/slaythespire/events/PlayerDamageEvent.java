package de.bundeswehr.auf.slaythespire.events;

import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Das Player Damage Event
 * @author OF Daniel Willig
 */
public class PlayerDamageEvent {
    private final Player player;
    private final int damageAmount;
    private final boolean damageFromCard;

    /**
     * Constructor PlayerDamageEvent
     *
     * @param player         der Spieler
     * @param damageAmount   zugefügter Schaden
     * @param damageFromCard bool ob es Schaden von einer Karte ist
     */
    public PlayerDamageEvent(Player player, int damageAmount, boolean damageFromCard) {
        this.player = player;
        this.damageAmount = damageAmount;
        this.damageFromCard = damageFromCard; //to check if damageSource is from a card
    }

    /**
     * getter PlayerEvent
     *
     * @return das PlayerEvent
     */
    public Player getPlayerEvent() {
        return player;
    }

    /**
     * getter DamageAmount
     *
     * @return Schadensmenge
     */
    public int getDamageAmount() {
        return damageAmount;
    }

    /**
     * ist der Schaden von einer Karte?
     *
     * @return boolean ob Schaden von Karte
     */
    public boolean isCard() {
        return damageFromCard;
    }
}
