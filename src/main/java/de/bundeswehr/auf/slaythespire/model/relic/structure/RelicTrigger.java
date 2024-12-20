package de.bundeswehr.auf.slaythespire.model.relic.structure;

/**
 * Trigger für Relics, um zu prüfen, wann das Relic triggern soll.
 *
 * @author L Frank Rieger
 */
public enum RelicTrigger {

    PICKUP,

    START_OF_COMBAT,
    FIRST_ATTACK,
    START_OF_TURN,
    PLAY_POTION,
    HP_CHANGED,
    EFFECT,
    END_OF_COMBAT,

    LOOT,
    REST_SITE,
    SHOP,
    TREASURE,

}
