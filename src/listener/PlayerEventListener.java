package listener;

import Events.PlayerBlockEvent;

public interface PlayerEventListener {
    void onBlockReceived(PlayerBlockEvent event);
}
