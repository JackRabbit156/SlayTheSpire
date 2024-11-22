package models.enemy.act_one;

import models.battle.GameContext;
import helper.PathAssistent;
import models.enemy.Enemy;
import models.enemy_card.act_one.acid_slime_enemy_cards.CorrosiveSpitEnemyCard;

/**
 * Diese Klasse repräsentiert den Gegner "Acid Slime" im ersten Akt des Spiels.
 * Der Acid Slime hat spezifische Eigenschaften und Fähigkeiten,
 * die im Kampf verwendet werden können.
 *
 * @author Warawa Alexander
 */
public class AcidSlimeEnemy extends Enemy {
    public AcidSlimeEnemy() {
        super("Acid Slime (L)", 65, 69);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        new CorrosiveSpitEnemyCard().play(gameContext);
    }
}