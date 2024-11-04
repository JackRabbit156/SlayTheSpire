package models.potion;

import models.GameContext;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;

import java.util.List;

public class AttackPotion extends Card {

    public AttackPotion() {
        super("Attack Potion", "Add 1 of 3 random Attack cards to your hand", 0, CardRarity.POTION, CardGrave.POTION);
    }

    @Override
    public void play(GameContext gameContext) {
        List<Card> hand = gameContext.getBattleDeck().getHand();

        //TODO Loot Vlad hinzufügen


    }
}
