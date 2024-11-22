package models.enemy.act_one.elites;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_one.elites.gremlin_nob_elite_cards.RushEnemyCard;
import models.enemy_card.act_one.elites.gremlin_nob_elite_cards.SkullBashEnemyCard;

import java.util.Random;
/**
 * @author Keil, Vladislav
 */
public class GremlinNobElite extends Enemy {
    public GremlinNobElite() {
        super("Gremlin Nob", 82, 86);
        setImagePath(new PathAssistent().toPath(this));
    }
    private Random randi = new Random();

    @Override
    public void attack(GameContext gameContext) {
        switch (randi.nextInt(2)) {
            case 0: new RushEnemyCard().play(gameContext); break;
            default: new SkullBashEnemyCard().play(gameContext);
        }
    }
}
