package models.enemy_card.act_two.boss.bronze_automaton_boss_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Hyper beam enemy card.
 *
 * @author OF Daniel Willig
 */
public class HyperBeamEnemyCard extends EnemyCard {
    /**
     * Constructor Hyper beam enemy card.
     */
    public HyperBeamEnemyCard() {
        super("Hyper Beam", "Deals 45 damage.", "45");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(45, false);
    }
}
