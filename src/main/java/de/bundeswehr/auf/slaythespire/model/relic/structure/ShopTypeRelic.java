package de.bundeswehr.auf.slaythespire.model.relic.structure;

import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;

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
     * @param rarity      Die Seltenheit
     */
    protected ShopTypeRelic(String name, String description, RelicRarity rarity, RelicTrigger relicTrigger) {
        super(name, description, rarity, relicTrigger);
    }

}
