package models.enemy_card.act_two.elites.book_of_stabbing_elite_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

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
