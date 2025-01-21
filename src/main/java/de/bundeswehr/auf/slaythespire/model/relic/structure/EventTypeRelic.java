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
     */
    protected EventTypeRelic(String name, String description, RelicTrigger relicTrigger) {
        super(name, description, RelicRarity.EVENT, relicTrigger);
    }

}
