package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.elite.spire_shield;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Smash enemy card.
 *
 * @author OF Daniel Willig
 */
public class SmashEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Smash enemy card.
     */
    public SmashEnemyCard() {
        super("Smash", "Deals 34 damage. Gains Block equal to its damage output.", 34);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        enemy.dealDamage(gameContext, getDamage(gameContext), player, this);
        enemy.gainBlock(gameContext.getAttackContext().getDamage());
    }

}
