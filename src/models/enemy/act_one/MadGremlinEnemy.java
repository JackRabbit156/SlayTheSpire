package models.enemy.act_one;

import helper.PathAssistent;
import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

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
        attackScratch(gameContext);
    }

    private void attackScratch(GameContext gameContext){
        int attackDamage = 4;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage,false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Scratch", player.getName(), attackDamage);
    }
}