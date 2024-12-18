package de.bundeswehr.auf.slaythespire.model.potion.structure;

import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

public abstract class AttackPotion extends Potion {

    private final int damage;

    /**
     * Constructor Potion card.
     *
     * @param name        der name
     * @param description die description
     * @param rarity      die rarity
     */
    public AttackPotion(String name, String description, int damage, CardRarity rarity) {
        super(name, description, rarity);
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

}
