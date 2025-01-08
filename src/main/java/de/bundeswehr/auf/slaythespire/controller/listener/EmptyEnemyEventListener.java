package de.bundeswehr.auf.slaythespire.controller.listener;

import de.bundeswehr.auf.slaythespire.events.EnemyBanterEvent;
import de.bundeswehr.auf.slaythespire.events.EnemyBlockEvent;
import de.bundeswehr.auf.slaythespire.events.EnemyDamageEvent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;

public class EmptyEnemyEventListener implements EnemyEventListener {

    @Override
    public void onDamageDealt(EnemyDamageEvent event) {}

    @Override
    public void onBanter(EnemyBanterEvent event) {}

    @Override
    public void onBlockReceived(EnemyBlockEvent event) {}

    @Override
    public void onDamageReceived(EnemyDamageEvent event) {}

    @Override
    public void onEnemyDeath(Enemy enemy) {}

}
