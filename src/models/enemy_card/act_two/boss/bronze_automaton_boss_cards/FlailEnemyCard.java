package models.enemy_card.act_two.boss.bronze_automaton_boss_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class FlailEnemyCard extends EnemyCard {
    public FlailEnemyCard() {
        super("Flail", "Deals 7 x 2 damage.", "7 x 2");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 2; i++) {
            player.decreaseCurrentHealth(7, false);
        }
    }
}
