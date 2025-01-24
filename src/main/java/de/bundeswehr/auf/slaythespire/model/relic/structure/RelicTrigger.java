package de.bundeswehr.auf.slaythespire.model.relic.structure;

/**
 * Trigger für Relics, um zu prüfen, wann das Relic triggern soll.
 *
 * @author L Frank Rieger
 */
public enum RelicTrigger {

    NONE,
    PICKUP,

    START_OF_COMBAT,
    BEGIN_OF_TURN,

    PLAY_CARD,
    PLAY_ATTACK,
    PLAY_POWER,
    PLAY_SKILL,
    PLAY_POTION,

    DISCARD,
    EXHAUST,

    GAIN_GOLD,
    GAIN_HP,
    LOSE_HP,
    EFFECT,

    END_OF_TURN,
    END_OF_COMBAT,

    LOOT,
    REST_SITE,
    SHOP,
    TREASURE,

}
