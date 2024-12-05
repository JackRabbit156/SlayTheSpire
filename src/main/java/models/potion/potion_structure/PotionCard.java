package models.potion.potion_structure;

import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.CardType;
import models.battle.GameContext;

/**
 * Die Potion card.
 * Genannt so, da sie im prinzip eine einmal einsetzbare Karte ist.
 *
 * @author OF Daniel Willig
 */
public abstract class PotionCard extends Card {
    /**
     * Constructor Potion card.
     *
     * @param name        der name
     * @param description die description
     * @param rarity      die rarity
     * @param cardType    der card type
     */
    public PotionCard(String name, String description, CardRarity rarity, CardType cardType) {
        super(name, description, 0, rarity, CardGrave.POTION, cardType);
    }


    @Override
    public abstract void play(GameContext gameContext);



    @Override
    public String toString() {
        return "";
    }
}