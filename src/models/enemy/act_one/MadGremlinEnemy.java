package models.enemy.act_one;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_one.mad_gremlin_enemy_cards.ScratchEnemyCard;

/**
 * Diese Klasse repräsentiert den Gegner "MadGremlin" im ersten Akt des Spiels.
 * Der Mad Gremlin hat spezifische Eigenschaften und Fähigkeiten,
 * die im Kampf verwendet werden können.
 *
 * @author Warawa Alexander
 */
public class MadGremlinEnemy extends Enemy {
    public MadGremlinEnemy() {
        super("Mad Gremlin", 20, 24);
        setImagePath(new PathAssistent().toPath(this));
    }


    @Override
    public void attack(GameContext gameContext) {
        new ScratchEnemyCard().play(gameContext);
    }

}