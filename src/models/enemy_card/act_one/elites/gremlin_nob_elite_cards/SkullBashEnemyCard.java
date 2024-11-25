package models.enemy_card.act_one.elites.gremlin_nob_elite_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

public class SkullBashEnemyCard extends EnemyCard {
    public SkullBashEnemyCard() {
        super("Skull Bash", "Deals 6 damage.", "6");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentHealth(6, false);
    }
}
