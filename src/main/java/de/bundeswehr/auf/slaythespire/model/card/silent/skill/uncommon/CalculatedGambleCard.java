package de.bundeswehr.auf.slaythespire.model.card.silent.skill.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Calculated Gamble Karte.
 *
 * @author L Frank Rieger
 */
public class CalculatedGambleCard extends SkillCard {

    public CalculatedGambleCard() {
        super("Calculated Gamble", "Discard your hand, then draw that many cards. Exhaust.", 0, CardRarity.UNCOMMON, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();

        List<Card> hand = new ArrayList<>(battleDeck.getHand());
        int amount = 0;
        for (Card card : hand) {
            battleDeck.discardCardFromHand(card);
            amount++;
        }

        battleDeck.drawCard(amount);

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

}