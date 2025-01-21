package de.bundeswehr.auf.slaythespire.model.card.silent.skill.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Deadly Poison Karte.
 *
 * @author L Frank Rieger
 */
public class DeadlyPoisonCard extends SkillCard {

    public DeadlyPoisonCard() {
        super("Deadly Poison", "Apply 5 Poison.", 1, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.addEffect(new PoisonDebuffEnemy(), 5);

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

}