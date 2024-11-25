package models.enemy_card.act_two.spheric_guardian_enemy_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class SlamSEnemyCard extends EnemyCard {
    public SlamSEnemyCard() {
        super("Slam", "Deals 10 x 2 damage.", "10 x 2");
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
