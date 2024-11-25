package models.enemy_card.act_four.elites.spire_shield_elite_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Smash enemy card.
 *
 * @author OF Daniel Willig
 */
public class SmashEnemyCard extends EnemyCard {
    /**
     * Constructor Smash enemy card.
     */
    public SmashEnemyCard() {
        super("Smash", "Deals 34 damage. Gains Block equal to its damage output.", "34");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        int oldHp = player.getCurrentHealth();
        player.decreaseCurrentHealth(34, false);
        enemy.addBlock(oldHp - player.getCurrentHealth());
    }
}
