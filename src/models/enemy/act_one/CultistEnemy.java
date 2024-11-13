package models.enemy.act_one;

import helper.PathAssistent;
import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

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
        attackDarkStrike(gameContext);
    }

    private void attackDarkStrike(GameContext gameContext){
        int attackDamage = 6;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }

}
