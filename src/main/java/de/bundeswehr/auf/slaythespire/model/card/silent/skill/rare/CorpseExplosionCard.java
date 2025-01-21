package de.bundeswehr.auf.slaythespire.model.card.silent.skill.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.CorpseExplosionDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Corpse Explosion Karte.
 *
 * @author L Frank Rieger
 */
public class CorpseExplosionCard extends SkillCard {

    public CorpseExplosionCard() {
        super("Corpse Explosion", "Apply 6 Poison. When the enemy dies, deal damage equal to its max HP to ALL enemies.", 2, CardRarity.RARE, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        gameContext.setRandomEnemy();
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.addEffect(new PoisonDebuffEnemy(), 6);
        enemy.addEffect(new CorpseExplosionDebuffEnemy(), 1);

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

}