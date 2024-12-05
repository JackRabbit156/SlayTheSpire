package models.enemy_card;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

/**
 * Die Insult enemy card.
 * Wenn keine andere Karte ausgeführt wird, wird diese hier genommen.
 *
 * @author OF Daniel Willig
 */
public class InsultEnemyCard extends EnemyCard {
    /**
     * Constructor Insult enemy card.
     */
    public InsultEnemyCard() {
        super("Insult", "Insult", "");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        // nothing will happen
    }
}
