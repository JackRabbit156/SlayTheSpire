package de.bundeswehr.auf.slaythespire.model.potion.structure;

import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardType;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;

/**
 * Die Potion card.
 * Genannt so, da sie im prinzip eine einmal einsetzbare Karte ist.
 *
 * @author OF Daniel Willig
 */
public abstract class Potion extends Card {
    /**
     * Constructor Potion card.
     *
     * @param name        der name
     * @param description die description
     * @param rarity      die rarity
     * @param cardType    der card type
     */
    public Potion(String name, String description, CardRarity rarity, CardType cardType) {
        super(name, description, 0, rarity, CardGrave.POTION, cardType);
    }


    @Override
    public abstract void play(GameContext gameContext);



    @Override
    public String toString() {
        return "";
    }
}