package models.enemy_card.act_two.byrd_enemy_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class SwoopEnemyCard extends EnemyCard {
    public SwoopEnemyCard() {
        super("Swoop", "Deals 12 damage.", "12");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(12, false);
    }
}
