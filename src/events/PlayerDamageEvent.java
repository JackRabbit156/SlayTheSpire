package events;

import models.player.player_structure.Player;

public class PlayerDamageEvent {
    private final Player player;
    private final int damageAmount;
    private final boolean damageFromCard;

    public PlayerDamageEvent(Player player, int damageAmount, boolean damageFromCard) {
        this.player = player;
        this.damageAmount = damageAmount;
        this.damageFromCard = damageFromCard; //to check if damageSource is from a card
    }

    public Player getPlayerEvent() {
        return player;
    }

    public int getDamageAmount() {
        return damageAmount;
    }

    public boolean isCard() {
        return damageFromCard;
    }
}
