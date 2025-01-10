package de.bundeswehr.auf.slaythespire.model.enemy_card.structure;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;

public abstract class MultiAttackEnemyCard extends EnemyCard {

    private final int damage;
    private final int multiplier;

    private static String replace(int damage, String description) {
        return description.replaceAll(" " + damage + " x", " " + GameSettings.getDifficultyLevel().modifyDamage(damage) + " x");
    }

    public MultiAttackEnemyCard(String name, String description, int damage, int multiplier) {
        super(name, replace(damage, description), GameSettings.getDifficultyLevel().modifyDamage(damage) + " x " + multiplier);
        this.damage = GameSettings.getDifficultyLevel().modifyDamage(damage);
        this.multiplier = multiplier;
    }

    public int dealDamage(GameContext gameContext) {
        return damage;
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        for (int i = 0; i < multiplier; i++) {
            enemy.dealDamage(gameContext, dealDamage(gameContext), player, this);
        }
    }

}
