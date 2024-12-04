package models.relic.relic_structure;

import models.player.player_structure.PlayerType;

/**
 * Es gibt verschiedene Typen von Relikten (z.B. anhand Act und Player-Char), hier werden diese definiert.
 *
 * @author OF Daniel Willig
 */
public abstract class PlayerTypeRelic extends Relic {

    /**
     * Der Player type.
     */
    private PlayerType playerType;


    /**
     * Constructor Player type relic.
     *
     * @param name        der Name
     * @param description Die Beschreibung
     * @param rarity      Die Seltenheit
     * @param playerType  der SpielerTyp
     */
    protected PlayerTypeRelic(String name, String description, RelicType rarity, PlayerType playerType) {
        super(name, description, rarity);
        this.playerType = playerType;
    }


    /**
     * getter player type.
     *
     * @return the player type
     */
    public PlayerType getPlayerType() {
        return playerType;
    }

    /**
     * setter player type.
     *
     * @param playerType the player type
     */
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

}
