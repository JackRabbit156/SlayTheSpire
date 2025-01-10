package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.status.DazedCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

/**
 * Die Reckless charge Karte.
 *
 * @author OF Daniel Willig
 */
public class RecklessChargeCard extends AttackCard {

    /**
     * Constructor Reckless charge card.
     */
    public RecklessChargeCard() {
        super("Reckless Charge", "Deal 7 damage. Shuffle a Dazed into your draw pile.", 0, 7, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);

        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.shuffleInDrawPile(new DazedCard());
    }
}
