package de.bundeswehr.auf.slaythespire.controller.listener;

import de.bundeswehr.auf.slaythespire.events.EnemyDamageEvent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;

public interface EnemyEventListener {

    /**
     * Wenn ein Gegner Schaden verursacht
     */
    void onDamageDealt(EnemyDamageEvent event);

    /**
     * Wenn ein Gegner Schaden erleidet.
     *
     * @param event das Event
     */
    void onDamageReceived(EnemyDamageEvent event);

    /**
     * Weinn ein Gegner gestorben ist.
     *
     * @param enemy der Gegner, der gestorben ist
     */
    void onEnemyDeath(Enemy enemy);

}
