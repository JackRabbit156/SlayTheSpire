package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.VulnerableDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Face slap enemy card.
 *
 * @author OF Daniel Willig
 */
public class FaceSlapEnemyCard extends AttackEnemyCard {

    /**
     * Constructor Face slap enemy card.
     */
    public FaceSlapEnemyCard() {
        super("Face Slap", "Deals 12 damage, applying 2 Vulnerable.", 12);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        super.playEnemy(gameContext, enemy);

        Player player = gameContext.getPlayer();
        player.addEffect(new VulnerableDebuff(), 2);
    }

}
