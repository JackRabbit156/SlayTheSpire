package events;

import models.player.player_structure.Player;

public class PlayerBlockEvent {
    private final Player player;
    private final int blockAmount;

    public PlayerBlockEvent(Player player, int blockAmount) {
        this.player = player;
        this.blockAmount = blockAmount;
    }

    public Player getPlayerEvent() {
        return player;
    }

    public int getBlockAmount() {
        return blockAmount;
    }

}
