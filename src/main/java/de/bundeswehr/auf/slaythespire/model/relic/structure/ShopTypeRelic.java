package de.bundeswehr.auf.slaythespire.model.relic.structure;

/**
 * Relikte, die in Shops gekauft werden können.
 *
 * @author L Frank Rieger
 */
public abstract class ShopTypeRelic extends Relic {

    /**
     * Constructor Player type relic.
     *
     * @param name        der Name
     * @param description Die Beschreibung
     */
    protected ShopTypeRelic(String name, String description, RelicTrigger relicTrigger) {
        super(name, description, RelicRarity.SHOP, relicTrigger);
    }

}
