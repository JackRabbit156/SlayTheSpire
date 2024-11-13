package models.enemy.act_one;

import helper.PathAssistent;
import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

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
        attackCorrosiveSpit(gameContext);
    }

    private void attackCorrosiveSpit(GameContext gameContext){
        int attackDamage = 11;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }

}