package models.card.silent.skill.common;

import helper.PathAssistent;
import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.SkillCard;
import models.battle.BattleDeck;
import models.battle.GameContext;
import models.player.player_structure.Player;

import java.util.List;
import java.util.Random;

/**
 * Die Survivor Karte.
 *
 * @author OF Daniel Willig
 */
public class SurvivorCard extends SkillCard {

    private static final Random rnd = new Random();

    /**
     * Constructor Survivor card.
     */
    public SurvivorCard() {
        super("Survivor", "Gain 8 Block. Discard 1 card.", 1, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();
        List<Card> hand = gameContext.getBattleDeck().getHand();

        player.setBlock(8);

        if (hand.size() > 1) {
            Card card;
            do {
                card = hand.get(rnd.nextInt(hand.size()));
            } while (card != this);
            battleDeck.discardCardFromHand(card);
        }

        player.decreaseCurrentEnergy(getCost());
    }

}