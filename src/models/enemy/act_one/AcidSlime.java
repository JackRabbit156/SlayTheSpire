package models.enemy.act_one;

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
public class AcidSlime extends Enemy {
    public AcidSlime() {
        super("Acid Slime (L)", 65, 96);
    }

    @Override
    public void attack(GameContext gameContext) {
        atttackCorrosiveSpit(gameContext);
    }

    private void atttackCorrosiveSpit(GameContext gameContext){
        int attackDamage = 11;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);
        System.out.printf("%s used %s, %s took %d damage!\n", getName(), "Dark Strike", player.getName(), attackDamage);
    }

}