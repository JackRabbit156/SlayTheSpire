package models.enemy_card.act_two.byrd_enemy_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Peck enemy card.
 *
 * @author OF Daniel Willig
 */
public class PeckEnemyCard extends EnemyCard {
    /**
     * Constructor Peck enemy card.
     */
    public PeckEnemyCard() {
        super("Peck", "Deals 1 x 5 damage.", "1 x 5");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 5; i++) {
            player.decreaseCurrentHealth(1, false);
        }
    }
}
