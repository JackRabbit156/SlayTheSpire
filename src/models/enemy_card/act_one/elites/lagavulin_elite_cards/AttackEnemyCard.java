package models.enemy_card.act_one.elites.lagavulin_elite_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class AttackEnemyCard extends EnemyCard {
    public AttackEnemyCard() {
        super("Attack", "Deal 18 damage.", "18");
        setImagePath(new PathAssistent().toPath(this));
    }


    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(18, false);
    }
}
