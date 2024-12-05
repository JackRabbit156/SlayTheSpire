package de.bundeswehr.auf.slaythespire.events;

import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;

/**
 * Das EnemyDamageEvent dient dazu notified zu werden, wenn ein Gegner schaden bekommt.
 * Manche Power-Karten können triggern wenn ein gegner schaden bekommt, etc.
 *
 * @author OF Daniel Willig
 */
public class EnemyDamageEvent {
    private final Enemy enemy;
    private final int damageAmount;


    /**
     * Constructor Enemy damage event.
     *
     * @param enemy        der Gegner
     * @param damageAmount Anzahl an Schaden. (Höhe an Schaden?, idk)
     */
    public EnemyDamageEvent(Enemy enemy, int damageAmount) {
        this.enemy = enemy;
        this.damageAmount = damageAmount;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public int getDamageAmount() {
        return damageAmount;
    }
}
