package de.bundeswehr.auf.slaythespire.model.card.ironclad.power.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardTrigger;
import de.bundeswehr.auf.slaythespire.model.card.structure.PowerCard;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Inflame Karte.
 *
 * @author L Frank Rieger
 */
public class InflameCard extends PowerCard {

    public InflameCard() {
        super("Inflame", "Gain 2 Strength.", 1, CardRarity.UNCOMMON, CardGrave.NONE, CardTrigger.NONE);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.addEffect(new StrengthBuff(), 2);

        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        // not triggered
    }

}
