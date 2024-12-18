package de.bundeswehr.auf.slaythespire.model.relic.structure;

/**
 * Relikte, die nicht durch Standard-Mechanismen erlangt werden können.
 *
 * @author L Frank Rieger
 */
public abstract class SpecialTypeRelic extends Relic {

    /**
     * Constructor Player type relic.
     *
     * @param name        der Name
     * @param description Die Beschreibung
     * @param rarity      Die Seltenheit
     */
    protected SpecialTypeRelic(String name, String description, RelicRarity rarity, RelicTrigger relicTrigger) {
        super(name, description, rarity, relicTrigger);
    }

}
