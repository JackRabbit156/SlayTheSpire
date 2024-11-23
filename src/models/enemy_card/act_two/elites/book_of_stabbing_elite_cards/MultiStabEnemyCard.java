package models.enemy_card.act_two.elites.book_of_stabbing_elite_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

import java.util.Random;

public class MultiStabEnemyCard extends EnemyCard {
    Random randi = new Random();
    int randomAttack = randi.nextInt(3);

    public MultiStabEnemyCard() {
        super("Multi Stab", "Deals 6 x N damage.", "6 x N");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();

        for (int i = 0; i < randomAttack; i++) {
            player.decreaseCurrentHealth(6, false);

        }
    }
}
