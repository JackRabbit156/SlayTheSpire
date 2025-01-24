package de.bundeswehr.auf.slaythespire.model.card.silent.power.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.attack.ShivCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardTrigger;
import de.bundeswehr.auf.slaythespire.model.card.structure.PowerCard;

/**
 * Die Infinite Blades Karte.
 *
 * @author L Frank Rieger
 */
public class InfiniteBladesCard extends PowerCard {

    public InfiniteBladesCard() {
        super("Infinite Blades", "At the start of your turn, add a Shiv to your hand.", 1, CardRarity.UNCOMMON, CardGrave.NONE, CardTrigger.PLAYER_BEGIN_OF_TURN);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.addToHand(new ShivCard());
    }

}