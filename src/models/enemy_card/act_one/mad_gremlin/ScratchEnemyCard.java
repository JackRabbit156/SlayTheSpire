package models.enemy_card.act_one.mad_gremlin;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Scratch enemy card.
 *
 * @author OF Daniel Willig
 */
public class ScratchEnemyCard extends EnemyCard {
    /**
     * Constructor Scratch enemy card.
     */
    public ScratchEnemyCard() {
        super("Scratch", "Deals 4 damage.", "4");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(4, false);
    }
}
