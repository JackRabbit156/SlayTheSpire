package models.enemy.act_one.boss;

import models.battle.GameContext;
import helper.PathAssistent;
import models.enemy.Enemy;
import models.enemy_card.act_one.boss.the_guardian_boss_cards.ChargingUpEnemyCard;
import models.enemy_card.act_one.boss.the_guardian_boss_cards.FierceBashEnemyCard;
import models.enemy_card.act_one.boss.the_guardian_boss_cards.WhirlwindEnemyCard;

import java.util.Random;

/**
 * @author Keil, Vladislav
 */
public class TheGuardianBoss extends Enemy {
    // https://slay-the-spire.fandom.com/wiki/The_Guardian
    public TheGuardianBoss() {
        super("The Guardian", 240, 240);
        setImagePath(new PathAssistent().toPath(this));
    }
    private Random randi = new Random();


    @Override
    public void attack(GameContext gameContext) {
        switch (randi.nextInt(3)) {
            case 0:  new FierceBashEnemyCard().play(gameContext); break;
            case 1: new ChargingUpEnemyCard().play(gameContext, this); break;
            default: new WhirlwindEnemyCard().play(gameContext);
        }
    }
}
