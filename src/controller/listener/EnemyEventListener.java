package controller.listener;

import events.EnemyDamageEvent;
import models.enemy.Enemy;

public interface EnemyEventListener {
    void onDamageReceived(EnemyDamageEvent event);
    void onEnemyDeath(Enemy enemy);

}
