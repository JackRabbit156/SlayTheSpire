package de.bundeswehr.auf.slaythespire.model.enemy_card.structure;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;

public abstract class AttackEnemyCard extends EnemyCard {

    private final int damage;

    private static String replace(int damage, String description) {
        return description.replaceAll(Integer.toString(damage), Integer.toString(GameSettings.getDifficultyLevel().modifyDamage(damage)));
    }

    public AttackEnemyCard(String name, String description, int damage) {
        super(name, replace(damage, description), Integer.toString(GameSettings.getDifficultyLevel().modifyDamage(damage)));
        this.damage = GameSettings.getDifficultyLevel().modifyDamage(damage);
    }

    public int getDamage(GameContext gameContext) {
        return damage;
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        enemy.dealDamage(gameContext, getDamage(gameContext), player, this);
    }

}
