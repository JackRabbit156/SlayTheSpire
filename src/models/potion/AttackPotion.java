package models.potion;

import helper.PathAssistent;
import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.GameContext;
import models.potion.potion_structure.PotionCard;

import java.util.List;

public class AttackPotion extends PotionCard {

    public AttackPotion() {
        super("Attack Potion", "Add 1 of 3 random Attack cards to your hand", 0, CardRarity.POTION, CardGrave.POTION);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Card> hand = gameContext.getBattleDeck().getHand();

        //TODO Loot Vlad hinzufügen


    }
}
