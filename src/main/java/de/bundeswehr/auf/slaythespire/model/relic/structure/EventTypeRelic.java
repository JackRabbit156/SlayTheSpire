package de.bundeswehr.auf.slaythespire.model.relic.structure;

/**
 * Relikte, die in einem Event erlangt werden können.
 *
 * @author L Frank Rieger
 */
public abstract class EventTypeRelic extends Relic {

    /**
     * Constructor Player type relic.
     *
     * @param name        der Name
     * @param description Die Beschreibung
     * @param rarity      Die Seltenheit
     */
    protected EventTypeRelic(String name, String description, RelicRarity rarity, RelicTrigger relicTrigger) {
        super(name, description, rarity, relicTrigger);
    }

}
