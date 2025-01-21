package de.bundeswehr.auf.slaythespire.model.card.silent.skill.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Leg Sweep Karte.
 *
 * @author L Frank Rieger
 */
public class LegSweepCard extends SkillCard {

    public LegSweepCard() {
        super("Leg Sweep", "Apply 2 Weak. Gain 11 Block.", 1, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.addEffect(new WeakDebuff(), 2);

        Player player = gameContext.getPlayer();
        player.gainBlock(11);

        player.decreaseCurrentEnergy(getCost());
    }

}