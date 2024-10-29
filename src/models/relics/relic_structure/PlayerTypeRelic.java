package models.relics.relic_structure;

import models.player.player_structure.PlayerType;

public abstract class PlayerTypeRelic extends Relic {
    // * Variables *
    private PlayerType playerType;


    // * Constructor *
    protected PlayerTypeRelic(String name, String description, RelicType rarity, PlayerType playerType) {
        super(name, description, rarity);
        this.playerType = playerType;
    }


    // * Getter & Setter *
    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
