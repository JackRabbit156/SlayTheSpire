package de.bundeswehr.auf.slaythespire.model.relic.structure;

/**
 * Relikte, die durch Besiegen eines Bosses erlangt werden können.
 *
 * @author L Frank Rieger
 */
public abstract class BossTypeRelic extends Relic {

    /**
     * Constructor Player type relic.
     *
     * @param name        der Name
     * @param description Die Beschreibung
     * @param rarity      Die Seltenheit
     */
    protected BossTypeRelic(String name, String description, RelicRarity rarity, RelicTrigger relicTrigger) {
        super(name, description, rarity, relicTrigger);
    }

}
