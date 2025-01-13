package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.VulnerableDebuff;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Face slap enemy card.
 *
 * @author OF Daniel Willig
 */
public class TauntEnemyCard extends EnemyCard {

    /**
     * Constructor Face slap enemy card.
     */
    public TauntEnemyCard() {
        super("Taunt", "Applies 2 Weak and 2 Vulnerable.", "2/2");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.addEffect(new WeakDebuff(), 2);
        player.addEffect(new VulnerableDebuff(), 2);
    }

}
