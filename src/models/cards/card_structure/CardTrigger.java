package models.cards.card_structure;

/**
 * Enum CardTrigger.
 * Für Power-Karten um zu prüfen wann die Power-Karte triggern soll
 * @author OF Daniel Willig
 */
public enum CardTrigger {
    ALWAYS, //does not trigger, sets something (like Strength)
    NEVER, //is never there

    PLAYER_BOT, //on beginning of Player turn
    PLAYER_EOT, //on end of Player turn

    GAIN_BLOCK, //on gaining Block
    GAIN_HP, //on gaining Health
    GAIN_ENERGY, //on gaining Energy

    LOSE_HP_CARD, //on losing life from a card
    LOSE_HP_ENEMY, //on losing life from an enemy

    PLAY_ATTACK, //on playing an attack Card
    PLAY_SKILL, //on playing an attack Card
    PLAY_POWER, //on playing an attack Card
}
