package models.enemy.act_four.boss;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_four.boss.corrupt_heart_boss_cards.BloodShotsEnemyCard;
import models.enemy_card.act_four.boss.corrupt_heart_boss_cards.EchoEnemyCard;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class CorruptHeartBoss extends Enemy {

    public CorruptHeartBoss() {
        super("CorruptHeart",750, 750);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        Random rand = new Random();
        switch (rand.nextInt(2)) {
            case 0: new BloodShotsEnemyCard().play(gameContext); break;
            case 1: new EchoEnemyCard().play(gameContext); break;
        }
    }
}
