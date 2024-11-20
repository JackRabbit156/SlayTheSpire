package events;

import models.enemy.Enemy;

public class EnemyDamageEvent {
    private final Enemy enemy;
    private final int damageAmount;


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
