package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

import java.util.List;

/**
 * Anger Karte.
 * @author OF Daniel Willig
 */
public class AngerCard extends AttackCard {

    /**
     * Constructor AngerCard
     */
    public AngerCard() {
        super("Anger", "Deal 6 damage. Add a copy of this card into your discard pile.", 0, 6, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        List<Card> discardPile = battleDeck.getDiscardPile();
        discardPile.add(new AngerCard());

        super.play(gameContext);
    }

}
