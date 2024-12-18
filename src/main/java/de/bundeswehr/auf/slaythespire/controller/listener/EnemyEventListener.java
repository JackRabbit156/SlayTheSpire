package de.bundeswehr.auf.slaythespire.controller.listener;

import de.bundeswehr.auf.slaythespire.events.EnemyDamageEvent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;

public interface EnemyEventListener {

    void onDamageReceived(EnemyDamageEvent event);
    void onEnemyDeath(Enemy enemy);

}
