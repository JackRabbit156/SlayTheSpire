package de.bundeswehr.auf.slaythespire.model.relic.structure;

import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;

/**
 * Relikte, die nur von einem Player-Char verwendet werden dürfen.
 *
 * @author OF Daniel Willig
 */
public abstract class StarterTypeRelic extends PlayerTypeRelic {

    /**
     * Constructor Player type relic.
     *
     * @param name        der Name
     * @param description Die Beschreibung
     * @param playerType  der SpielerTyp
     */
    protected StarterTypeRelic(String name, String description, PlayerType playerType, RelicTrigger relicTrigger) {
        super(name, description, RelicRarity.STARTER, playerType, relicTrigger);
    }

}
