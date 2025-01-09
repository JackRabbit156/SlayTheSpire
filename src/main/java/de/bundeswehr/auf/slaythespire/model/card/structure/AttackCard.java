package de.bundeswehr.auf.slaythespire.model.card.structure;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

public abstract class AttackCard extends Card {

    private final int damage;

    public AttackCard(String name, String description, int cost, int damage, CardRarity rarity, CardGrave cardGrave) {
        super(name, description, cost, rarity, cardGrave);
        this.damage = damage;
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage(gameContext), gameContext);

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    public int dealDamage(GameContext gameContext) {
        return getDamage();
    }

    public int getDamage() {
        return this.damage;
    }

}