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

    public int getDamage(GameContext gameContext) {
        return getDamage();
    }

    public int getDamage() {
        return this.damage;
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        Enemy enemy = gameContext.getSelectedEnemy();
        player.dealDamage(gameContext, getDamage(gameContext), enemy, this);

        player.decreaseCurrentEnergy(getCost());
    }

}