package de.bundeswehr.auf.slaythespire.model.card.structure;

public abstract class SkillCard extends Card {

    public SkillCard(String name, String description, int cost, CardRarity rarity, CardGrave cardGrave) {
        super(name, description, cost, rarity, cardGrave);
    }

}