package models.cards.card_structure;

import models.GameContext;

public abstract class AttackCard extends Card {
    private int damage;
    public AttackCard(String name, String description, int cost, int damage,CardRarity rarity, CardGrave cardGrave) {
        super(name, description, cost, rarity, cardGrave, CardType.ATTACK);
        this.damage = damage;
    }

    public int getDamage() { return this.damage; }

    @Override
    public abstract void play(GameContext gameContext);

    public abstract int dealDamage();


    @Override
    public String toString() {
        return getName() + "(Energy: -"+getCost()+", "+"Damage: "+ damage+")";
    }
}