package de.bundeswehr.auf.slaythespire.model.card.ironclad.power.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardTrigger;
import de.bundeswehr.auf.slaythespire.model.card.structure.PowerCard;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.VulnerableDebuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Berserk Karte.
 *
 * @author OF Daniel Willig
 */
public class BerserkCard extends PowerCard {

    /**
     * Constructor Berserk card.
     */
    public BerserkCard() {
        super("Berserk", "Gain 2 Vulnerable. At the start of your turn, gain 1 Energy.", 0, CardRarity.RARE, CardGrave.NONE, CardTrigger.PLAYER_BEGIN_OF_TURN);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.addEffect(new VulnerableDebuff(), 2);

        super.play(gameContext);
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        player.gainEnergy(1);
    }

}
