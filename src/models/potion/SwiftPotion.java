package models.potion;

import models.battle.BattleDeck;
import models.battle.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.potion.potion_structure.PotionCard;

public class SwiftPotion extends PotionCard {

    public SwiftPotion() {
        super("Swift Potion", "Draw 3 cards.", 0, CardRarity.POTION, CardGrave.POTION);
    }

    @Override
    public void play(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(1);
    }
}
