package models.enemy_card.act_two.boss.the_champ_boss_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class ExecuteEnemyCard extends EnemyCard {
    public ExecuteEnemyCard() {
        super("Execute", "Deals 10 x 2 damage.", "10 x 2");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 2; i++) {
            player.decreaseCurrentHealth(10, false);
        }
    }
}
