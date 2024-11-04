package models.enemy.act_one;

import models.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;

public class AcidSlime extends Enemy {
    public AcidSlime() {
        super("Acid Slime (L)", 65, 96);
    }

    @Override
    public void attack(GameContext gameContext) {

        attackCorrosiveSpit(gameContext);

    }

    private void attackCorrosiveSpit(GameContext gameContext){
        int attackDamage = 11;
        Player player = gameContext.getPlayer();

        player.decreaseCurrentHealth(attackDamage, false);

    }





}