package models.enemy.act_four.elites;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_four.elites.spire_shield_elite_cards.SmashEnemyCard;
import models.player.player_structure.Player;

/**
 * @author Keil, Vladislav
 */
public class SpireShieldElite extends Enemy {
    public SpireShieldElite() {
        super("Spire Shield",160, 160);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        new SmashEnemyCard().play(gameContext);
    }
}
