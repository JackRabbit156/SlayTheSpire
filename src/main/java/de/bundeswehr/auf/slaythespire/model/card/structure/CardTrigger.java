package de.bundeswehr.auf.slaythespire.model.card.structure;

/**
 * Enum CardTrigger.
 * Für Power-Karten um zu prüfen wann die Power-Karte triggern soll
 * @author OF Daniel Willig
 */
public enum CardTrigger {

    NONE,

    PLAYER_BEGIN_OF_TURN, // on beginning of player turn
    PLAYER_END_OF_TURN, // on end of player turn

    GAIN_BLOCK, // on gaining block
    GAIN_HP, // on gaining health
    GAIN_ENERGY, // on gaining energy

    LOSE_HP_CARD, // on losing life from a card
    LOSE_HP_ENEMY, // on losing life from an enemy

    PLAY_CARD, // on playing any card
    PLAY_ATTACK, // on playing an attack card
    PLAY_SKILL, // on playing a skill card
    PLAY_POWER // on playing a power card

}
