package de.bundeswehr.auf.slaythespire.events;

import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;

/**
 * Das Enemy Block Event
 *
 * @author L Frank Rieger
 */
public class EnemyBlockEvent {

    private final int blockAmount;
    private final Enemy enemy;

    public EnemyBlockEvent(Enemy enemy, int blockAmount) {
        this.enemy = enemy;
        this.blockAmount = blockAmount;
    }

    public int getBlockAmount() {
        return blockAmount;
    }

    public Enemy getEnemy() {
        return enemy;
    }

}
