package de.bundeswehr.auf.slaythespire.model.relic.structure;

import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;

/**
 * Relikte, die nur von einem Player-Char verwendet werden dürfen.
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
    protected PlayerTypeRelic(String name, String description, RelicRarity rarity, PlayerType playerType, RelicTrigger relicTrigger) {
        super(name, description, rarity, relicTrigger);
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
