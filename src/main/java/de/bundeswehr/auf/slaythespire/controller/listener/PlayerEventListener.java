package de.bundeswehr.auf.slaythespire.controller.listener;

import de.bundeswehr.auf.slaythespire.events.*;

/**
 * Interface Player event de.bundeswehr.auf.slaythespire.controller.listener.
 *
 * @author OF Daniel Willig
 */
public interface PlayerEventListener {

    /**
     * Wenn Block hinzugefügt wird
     *
     * @param event das Event
     */
    void onBlockReceived(PlayerBlockEvent event);

    /**
     * Wenn Spieler Schaden verursacht
     */
    void onDamageDealt(PlayerDamageEvent event);

    /**
     * Wenn Spieler Schaden erleidet
     *
     * @param event das Event
     */
    void onDamageReceived(PlayerDamageEvent event);

    /**
     * Wenn Spieler einen Effekt bekommt
     *
     * @param event das Event
     */
    void onEffect(EffectEvent event);

    /**
     * Wenn Spieler Schaden bekommt
     *
     * @param event das Event
     */
    void onEnergyReceived(PlayerEnergyEvent event);

    /**
     * Wenn Spieler Schaden bekommt
     *
     * @param event das Event
     */
    void onHealthReceived(PlayerHealthEvent event);

    /**
     * Wenn die maximale Gesundheit verändert wird.
     *
     * @param event das Event
     */
    void onMaxHealthChanged(PlayerHealthEvent event);

}
