package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.elite.spire_shield;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Bash enemy card.
 *
 * @author L Frank Rieger
 */
public class BashEnemyCard extends AttackEnemyCard {

    public BashEnemyCard() {
        super("Bash", "Deals 12 damage. Applies -1 Strength.", 12);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        enemy.dealDamage(gameContext, getDamage(gameContext), player, this);
        player.addEffect(new StrengthBuff(), -1);
    }

}
