package de.bundeswehr.auf.slaythespire.controller.listener;

import de.bundeswehr.auf.slaythespire.events.EnemyBanterEvent;
import de.bundeswehr.auf.slaythespire.events.EnemyBlockEvent;
import de.bundeswehr.auf.slaythespire.events.EnemyDamageEvent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;

public interface EnemyEventListener {

    /**
     * Wenn ein Gegner nicht angreift.
     */
    void onBanter(EnemyBanterEvent event);

    /**
     * Wenn ein Gegner Schaden erleidet.
     */
    void onBlockReceived(EnemyBlockEvent event);

    /**
     * Wenn ein Gegner Schaden verursacht
     */
    void onDamageDealt(EnemyDamageEvent event);

    /**
     * Wenn ein Gegner Schaden erleidet.
     */
    void onDamageReceived(EnemyDamageEvent event);

    /**
     * Weinn ein Gegner gestorben ist.
     *
     * @param enemy der Gegner, der gestorben ist
     */
    void onEnemyDeath(Enemy enemy);

}
