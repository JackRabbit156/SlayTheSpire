package models.potion;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;

import java.util.List;

public class DistilledChaosPotion extends Card {

    public DistilledChaosPotion() {
        super("Distilled Chaos", "Play the top 3 cards of your draw pile.", 0, CardRarity.POTION, CardGrave.POTION);
    }

    @Override
    public void play(GameContext gameContext) {
        List<Card> deck = gameContext.getBattleDeck().getDeck();

        for (int i = 1; i <= 3; i++) {
            deck.get(deck.size() - i).play(gameContext);
        }

    }
}
