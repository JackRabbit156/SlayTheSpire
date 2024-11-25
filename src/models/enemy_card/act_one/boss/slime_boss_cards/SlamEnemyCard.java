package models.enemy_card.act_one.boss.slime_boss_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class SlamEnemyCard extends EnemyCard {
    public SlamEnemyCard() {
        super("Slam", "Deals 35 damage.", "35");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(35, false);
    }
}
