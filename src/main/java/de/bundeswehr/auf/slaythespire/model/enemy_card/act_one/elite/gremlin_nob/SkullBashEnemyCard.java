package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.elite.gremlin_nob;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Skull bash enemy card.
 *
 * @author OF Daniel Willig
 */
public class SkullBashEnemyCard extends EnemyCard {
    /**
     * Constructor Skull bash enemy card.
     */
    public SkullBashEnemyCard() {
        super("Skull Bash", "Deals 6 damage.", "6");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(6, false);
    }
}
