package de.bundeswehr.auf.slaythespire.model.enemy_card.structure;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;

public abstract class BlockEnemyCard extends EnemyCard {

    private final int block;

    private static String replace(int damage, String description) {
        return description.replaceAll(Integer.toString(damage), Integer.toString(GameSettings.getDifficultyLevel().modifyDamage(damage)));
    }

    public BlockEnemyCard(String name, String description, int block) {
        super(name, replace(block, description), "");
        this.block = GameSettings.getDifficultyLevel().modifyDamage(block);
    }

    public int gainBlock(GameContext gameContext) {
        return block;
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        enemy.gainBlock(gainBlock(gameContext));
    }

}
