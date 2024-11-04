package models.potion;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;

public class SwiftPotion extends Card {

    public SwiftPotion() {
        super("Swift Potion", "Draw 3 cards.", 0, CardRarity.POTION, CardGrave.POTION);
    }

    @Override
    public void play(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(1);
    }
}
