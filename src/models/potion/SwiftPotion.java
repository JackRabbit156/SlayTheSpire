package models.potion;

import helper.PathAssistent;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.BattleDeck;
import models.battle.GameContext;
import models.card.card_structure.CardType;
import models.potion.potion_structure.PotionCard;

public class SwiftPotion extends PotionCard {

    public SwiftPotion() {
        super("Swift Potion", "Draw 3 cards.", 0, CardRarity.COMMON, CardGrave.POTION, CardType.SKILL);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(1);
    }
}
