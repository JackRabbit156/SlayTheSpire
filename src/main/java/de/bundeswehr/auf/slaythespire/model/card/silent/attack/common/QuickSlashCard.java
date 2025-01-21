package de.bundeswehr.auf.slaythespire.model.card.silent.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

/**
 * Die Quick Slash Karte.
 *
 * @author L Frank Rieger
 */
public class QuickSlashCard extends AttackCard {

    public QuickSlashCard() {
        super("Quick Slash", "Deal 8 damage. Draw 1 card.", 1, 8, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);

        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(1);
    }

}