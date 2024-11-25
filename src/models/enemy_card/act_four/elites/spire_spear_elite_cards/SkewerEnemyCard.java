package models.enemy_card.act_four.elites.spire_spear_elite_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class SkewerEnemyCard extends EnemyCard {
    public SkewerEnemyCard() {
        super("Skewer", "Deals 10 x 3 damage.", "10 x 3");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < 3; i++) {
            player.decreaseCurrentHealth(10, false);
        }
    }
}
