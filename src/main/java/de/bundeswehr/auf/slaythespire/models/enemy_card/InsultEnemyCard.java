package de.bundeswehr.auf.slaythespire.models.enemy_card;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.enemy_card.enemy_card_structure.EnemyCard;

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
