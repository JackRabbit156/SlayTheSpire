package de.bundeswehr.auf.slaythespire.model.card.silent.skill.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.silent.attack.ShivCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Storm of Steel Karte.
 *
 * @author L Frank Rieger
 */
public class StormOfStealCard extends SkillCard {

    public StormOfStealCard() {
        super("Storm of Steel", "Discard your hand. Add 1 Shiv into your hand for each card discarded.", 1, CardRarity.RARE, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        List<Card> hand = new ArrayList<>(battleDeck.getHand());
        for (Card card : hand) {
            if (card != this) {
                battleDeck.discardCardFromHand(card);
            }
        }
        for (int i = 0; i < hand.size() - 1; i++) {
            battleDeck.addToHand(new ShivCard());
        }

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(player.getCurrentEnergy());
    }

}