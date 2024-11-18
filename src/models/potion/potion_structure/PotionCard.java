package models.potion.potion_structure;

import models.battle.GameContext;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.CardType;

public abstract class PotionCard extends Card {
    public PotionCard(String name, String description, int cost, CardRarity rarity, CardGrave cardGrave) {
        super(name, description, cost, rarity, cardGrave, CardType.POTION);
    }


    @Override
    public abstract void play(GameContext gameContext);



    @Override
    public String toString() {
        return "";
    }
}