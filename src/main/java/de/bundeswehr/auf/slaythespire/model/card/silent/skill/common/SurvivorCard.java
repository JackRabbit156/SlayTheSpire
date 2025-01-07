package de.bundeswehr.auf.slaythespire.model.card.silent.skill.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

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
        player.increaseBlock(8);

        BattleDeck battleDeck = gameContext.getBattleDeck();
        List<Card> hand = gameContext.getBattleDeck().getHand();
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