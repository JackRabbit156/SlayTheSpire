package models.potion;

import helper.PathAssistent;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.BattleDeck;
import models.battle.GameContext;
import models.card.card_structure.CardType;
import models.potion.potion_structure.PotionCard;

/**
 * Die Swift potion.
 *
 * @author OF Daniel Willig
 */
public class SwiftPotion extends PotionCard {

    /**
     * Constructor Swift potion.
     */
    public SwiftPotion() {
        super("Swift Potion", "Draw 3 cards.",  CardRarity.COMMON,  CardType.SKILL);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(3);
    }
}
