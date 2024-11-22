package models.enemy.act_one.elites;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_one.elites.lagavulin_elite_cards.AttackEnemyCard;

/**
 * @author Keil, Vladislav
 */
public class LagavulinElite extends Enemy {
    public LagavulinElite() {
        super("Lagavulin", 109, 111);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        new AttackEnemyCard().play(gameContext);
    }
}
