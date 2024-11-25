package models.enemy_card.act_one.elites.gremlin_nob_elite_cards;

import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class RushEnemyCard extends EnemyCard {
    public RushEnemyCard() {
        super("Rush", "Deals 14 damage.", "14");
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(14, false);
    }
}
