package models.potion.potion_structure;

import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.CardType;
import models.battle.GameContext;

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