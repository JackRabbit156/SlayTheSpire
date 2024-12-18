package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.cultist;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Dark strike enemy card.
 *
 * @author OF Daniel Willig
 */
public class DarkStrikeEnemyCard extends EnemyCard {
    /**
     * Constructor Dark strike enemy card.
     */
    public DarkStrikeEnemyCard() {
        super("Dark Strike", "Deal 6 damage.", "6");
        setImagePath(new PathAssistent().toPath(this));
    }


    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(6, false);
    }


}
