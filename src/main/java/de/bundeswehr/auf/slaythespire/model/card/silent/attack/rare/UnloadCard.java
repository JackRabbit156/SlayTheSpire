package de.bundeswehr.auf.slaythespire.model.card.silent.attack.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Unload Karte.
 *
 * @author L Frank Rieger
 */
public class UnloadCard extends AttackCard {

    public UnloadCard() {
        super("Unload", "Deal 14 damage. Discard all non-Attack cards in your hand.", 1, 14, CardRarity.RARE, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);

        BattleDeck battleDeck = gameContext.getBattleDeck();
        List<Card> hand = new ArrayList<>(battleDeck.getHand());
        for (Card card : hand) {
            if (!(card instanceof AttackCard)) {
                battleDeck.discardCardFromHand(card);
            }
        }
    }

}