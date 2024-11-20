package models.potion;

import helper.PathAssistent;
import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.GameContext;
import models.potion.potion_structure.PotionCard;

import java.util.List;

public class DistilledChaosPotion extends PotionCard {

    public DistilledChaosPotion() {
        super("Distilled Chaos", "Play the top 3 cards of your draw pile.", 0, CardRarity.UNCOMMON, CardGrave.POTION);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Card> deck = gameContext.getBattleDeck().getDeck();

        for (int i = 1; i <= 3; i++) {
            deck.get(deck.size() - i).play(gameContext);
        }

    }
}
