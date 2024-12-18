package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.boss.the_champ;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Heavy slash enemy card.
 *
 * @author OF Daniel Willig
 */
public class HeavySlashEnemyCard extends EnemyCard {
    /**
     * Constructor Heavy slash enemy card.
     */
    public HeavySlashEnemyCard() {
        super("Heavy Slash", "Deals 16 damage.", "16");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(16, false);
    }
}
