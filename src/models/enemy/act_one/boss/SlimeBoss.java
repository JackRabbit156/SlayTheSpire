package models.enemy.act_one.boss;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_one.boss.slime_boss_cards.SlamEnemyCard;
import models.enemy_card.act_one.cultist_enemy_cards.DarkStrikeEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class SlimeBoss extends Enemy {
    // https://slay-the-spire.fandom.com/wiki/Slime_Boss
    private Random rand = new Random();

    public SlimeBoss() {
        super("Slime Boss", 150, 150);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new SlamEnemyCard());

        setEnemyDeck(deck);
    }

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}
