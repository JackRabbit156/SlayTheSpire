package listener;

import events.PlayerBlockEvent;
import events.PlayerDamageEvent;

/**
 * Interface Player event listener.
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
}
