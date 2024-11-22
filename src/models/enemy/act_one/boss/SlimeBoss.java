package models.enemy.act_one.boss;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_one.boss.slime_boss_cards.SlamEnemyCard;

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
    }

    @Override
    public void attack(GameContext gameContext) {
        new SlamEnemyCard().play(gameContext);
    }
}
