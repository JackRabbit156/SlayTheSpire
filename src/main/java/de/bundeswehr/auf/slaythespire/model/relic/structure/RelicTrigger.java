package de.bundeswehr.auf.slaythespire.model.relic.structure;

/**
 * Trigger für Relics, um zu prüfen, wann das Relic triggern soll.
 *
 * @author L Frank Rieger
 */
public enum RelicTrigger {

    PICKUP,

    START_OF_COMBAT,
    START_OF_TURN,
    PLAY_ATTACK,
    PLAY_POTION,
    GAIN_HP,
    LOSE_HP,
    EFFECT,
    END_OF_COMBAT,

    LOOT,
    REST_SITE,
    SHOP,
    TREASURE,

}
