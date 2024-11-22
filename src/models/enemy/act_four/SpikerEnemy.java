package models.enemy.act_four;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_four.spiker_enemy_cards.CutEnemyCard;
import models.player.player_structure.Player;


/**
 * @author Keil, Vladislav
 */
public class SpikerEnemy extends Enemy {
    public SpikerEnemy() {
        super("Shapes",42, 56);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        new CutEnemyCard().play(gameContext);
    }
}
