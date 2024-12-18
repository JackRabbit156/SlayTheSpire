package de.bundeswehr.auf.slaythespire.model.potion.structure;

import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

public abstract class SkillPotion extends Potion {

    /**
     * Constructor Potion card.
     *
     * @param name        der name
     * @param description die description
     * @param rarity      die rarity
     */
    public SkillPotion(String name, String description, CardRarity rarity) {
        super(name, description, rarity);
    }

}
