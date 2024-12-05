package de.bundeswehr.auf.slaythespire.controller.listener;

import de.bundeswehr.auf.slaythespire.events.PlayerBlockEvent;
import de.bundeswehr.auf.slaythespire.events.PlayerDamageEvent;

/**
 * Interface Player event de.bundeswehr.auf.slaythespire.controller.listener.
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
     * Wenn Spieler Schaden bekommt
     *
     * @param event das Event
     */
    void onDamageReceived(PlayerDamageEvent event);

    void onDamageDealt();
}
