package listener;

import events.PlayerBlockEvent;
import events.PlayerDamageEvent;

public interface PlayerEventListener {
    void onBlockReceived(PlayerBlockEvent event);
    void onDamageReceived(PlayerDamageEvent event);
}
