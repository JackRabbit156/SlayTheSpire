package models.enemy.act_four.elites;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_four.elites.spire_spear_elite_cards.SkewerEnemyCard;
import models.player.player_structure.Player;

/**
 * @author Keil, Vladislav
 */
public class SpireSpearElite extends Enemy {
    public SpireSpearElite() {
        super("Spire Spear",160, 160);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        new SkewerEnemyCard().play(gameContext);
    }
}
