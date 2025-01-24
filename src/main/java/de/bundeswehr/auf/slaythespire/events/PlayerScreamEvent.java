package de.bundeswehr.auf.slaythespire.events;

import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * @author L Frank Rieger
 */
public class PlayerScreamEvent {

    private final Player player;
    private final String text;

    public PlayerScreamEvent(Player player, String text) {
        this.player = player;
        this.text = text;
    }

    public Player getPlayer() {
        return player;
    }

    public String getText() {
        return text;
    }

}
