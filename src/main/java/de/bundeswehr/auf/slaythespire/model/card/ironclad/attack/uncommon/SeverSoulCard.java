package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

import java.util.List;

/**
 * Die Sever soul Karte.
 *
 * @author OF Daniel Willig
 */
public class SeverSoulCard extends AttackCard {

    /**
     * Constructor Sever soul card.
     */
    public SeverSoulCard() {
        super("Sever Soul", "Exhaust all non-Attack cards in your hand. Deal 16 damage.", 2, 16, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);

        BattleDeck battleDeck = gameContext.getBattleDeck();
        List<Card> hand = battleDeck.getHand();
        for (int i = hand.size() - 1; i >= 0; i--) {
            Card card = hand.get(i);
            if (!(card instanceof AttackCard)) {
                battleDeck.exhaustCardFromHand(card);
            }
        }
    }
}
