package de.bundeswehr.auf.slaythespire.model.card.ironclad.power.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardTrigger;
import de.bundeswehr.auf.slaythespire.model.card.structure.PowerCard;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Rupture Karte.
 *
 * @author L Frank Rieger
 */
public class RuptureCard extends PowerCard {

    /**
     * Constructor Entrench card.
     */
    public RuptureCard() {
        super("Rupture", "Whenever you lose HP from a card, gain 1 Strength.", 1, CardRarity.UNCOMMON, CardGrave.NONE, CardTrigger.LOSE_HP_CARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.addEffect(new StrengthBuff(), 1);
    }

}
