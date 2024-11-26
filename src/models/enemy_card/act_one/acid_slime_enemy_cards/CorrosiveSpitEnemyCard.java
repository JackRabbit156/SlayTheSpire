package models.enemy_card.act_one.acid_slime_enemy_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Corrosive spit enemy card.
 *
 * @author OF Daniel Willig
 */
public class CorrosiveSpitEnemyCard extends EnemyCard {
    /**
     * Constructor Corrosive spit enemy card.
     */
    public CorrosiveSpitEnemyCard() {
        super("Corrosive Spit", "Deals 11 damage.", "11");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(11, false);
    }
}
