package de.bundeswehr.auf.slaythespire.model.card.silent.skill.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Catalyst Karte.
 *
 * @author L Frank Rieger
 */
public class CatalystCard extends SkillCard {

    public CatalystCard() {
        super("Catalyst", "Double an enemy's Poison. Exhaust.", 0, CardRarity.UNCOMMON, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        PoisonDebuffEnemy effect = new PoisonDebuffEnemy();
        player.addEffect(effect, player.getEffectCounter(effect));

        player.decreaseCurrentEnergy(getCost());
    }

}