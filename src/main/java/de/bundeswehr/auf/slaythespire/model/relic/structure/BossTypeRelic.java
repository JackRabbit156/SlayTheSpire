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
     */
    protected BossTypeRelic(String name, String description, RelicTrigger relicTrigger) {
        super(name, description, RelicRarity.BOSS, relicTrigger);
    }

}
