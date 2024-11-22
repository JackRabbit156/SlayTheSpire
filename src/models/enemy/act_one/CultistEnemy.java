package models.enemy.act_one;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_one.cultist_enemy_cards.DarkStrikeEnemyCard;

/**
 * Diese Klasse repräsentiert den Gegner "Cultist" im ersten Akt des Spiels.
 * Der Cultist hat spezifische Eigenschaften und Fähigkeiten,
 * die im Kampf verwendet werden können.
 *
 * @author Warawa Alexander
 */
public class CultistEnemy extends Enemy {
    public CultistEnemy() {
        super("Cultist", 48, 54);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        new DarkStrikeEnemyCard().play(gameContext);
    }
}
