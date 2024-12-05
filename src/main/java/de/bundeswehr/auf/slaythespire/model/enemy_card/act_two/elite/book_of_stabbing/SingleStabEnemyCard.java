package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.elite.book_of_stabbing;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Single stab enemy card.
 *
 * @author OF Daniel Willig
 */
public class SingleStabEnemyCard extends EnemyCard {
    /**
     * Constructor Single stab enemy card.
     */
    public SingleStabEnemyCard() {
        super("Corrosive Spit", "Deals 21 damage.", "21");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(21, false);
    }
}
