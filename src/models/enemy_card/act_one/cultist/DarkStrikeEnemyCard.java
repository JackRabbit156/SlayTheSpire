package models.enemy_card.act_one.cultist;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

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
