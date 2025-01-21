package de.bundeswehr.auf.slaythespire.model.card.silent.skill.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;

/**
 * Die Survivor Karte.
 * Survivor discards the last card in your hand.
 * Survivor can be played as the last card in your hand without needing to discard anything.
 *
 * @author OF Daniel Willig
 */
public class SurvivorCard extends SkillCard {

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
        player.gainBlock(8);

        BattleDeck battleDeck = gameContext.getBattleDeck();
        List<Card> hand = battleDeck.getHand();
        if (hand.size() > 1) {
            Card card;
            int i = hand.size();
            do {
                card = hand.get(--i);
            } while (card == this);
            battleDeck.discardCardFromHand(card);
        }

        player.decreaseCurrentEnergy(getCost());
    }

}