package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.battle.Playable;
import de.bundeswehr.auf.slaythespire.model.card.structure.*;

import java.util.List;

/**
 * Clash Karte.
 *
 * @author OF Daniel Willig
 */
public class ClashCard extends AttackCard {

    /**
     * Constructor ClashCard.
     */
    public ClashCard() {
        super("Clash", "Can only be played if every card in your hand is an Attack. Deal 14 damage.", 0, 14, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public Playable isPlayable(GameContext gameContext) {
        List<Card> hand = gameContext.getBattleDeck().getHand();
        for (Card card : hand) {
            if (!(card instanceof AttackCard)) {
                return new Playable("Clash not playable due to " + card.getName(), "Can only be played, if every card in your hand is an Attack.");
            }
        }
        return super.isPlayable(gameContext);
    }

}
