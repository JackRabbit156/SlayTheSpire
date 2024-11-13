package models.potion;

import helper.PathAssistent;
import models.BattleDeck;
import models.GameContext;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.potion.potion_structure.PotionCard;

public class SwiftPotion extends PotionCard {

    public SwiftPotion() {
        super("Swift Potion", "Draw 3 cards.", 0, CardRarity.POTION, CardGrave.POTION);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(1);
    }
}
