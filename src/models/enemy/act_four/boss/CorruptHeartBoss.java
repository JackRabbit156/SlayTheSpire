package models.enemy.act_four.boss;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_four.boss.corrupt_heart_boss_cards.BloodShotsEnemyCard;
import models.enemy_card.act_four.boss.corrupt_heart_boss_cards.EchoEnemyCard;
import models.enemy_card.act_one.cultist_enemy_cards.DarkStrikeEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class CorruptHeartBoss extends Enemy {
    private Random rand = new Random();

    public CorruptHeartBoss() {
        super("CorruptHeart",750, 750);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new BloodShotsEnemyCard());
        deck.add(new EchoEnemyCard());

        setEnemyDeck(deck);
    }

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}
