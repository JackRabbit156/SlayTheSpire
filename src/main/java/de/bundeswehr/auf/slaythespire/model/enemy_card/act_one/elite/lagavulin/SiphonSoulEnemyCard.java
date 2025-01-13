package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.elite.lagavulin;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Attack enemy card.
 *
 * @author OF Daniel Willig
 */
public class SiphonSoulEnemyCard extends EnemyCard {

    /**
     * Constructor Attack enemy card.
     */
    public SiphonSoulEnemyCard() {
        super("Siphon Soul", "Inflicts -1 Strength.", "");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.addEffect(new StrengthBuff(), -1);
    }

}
