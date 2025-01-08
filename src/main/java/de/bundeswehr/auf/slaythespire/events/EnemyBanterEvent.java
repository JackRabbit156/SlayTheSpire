package de.bundeswehr.auf.slaythespire.events;

import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;

/**
 * Das EnemyBanterEvent dient dazu notified zu werden, wenn ein Gegner nichts macht.
 *
 * @author L Frank Rieger
 */
public class EnemyBanterEvent {

    private final Enemy enemy;
    private final String banter;

    public EnemyBanterEvent(Enemy enemy, String banter) {
        this.enemy = enemy;
        this.banter = banter;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public String getBanter() {
        return banter;
    }

}
