package models.cards.card_structure;

import models.battle.GameContext;

public abstract class SkillCard extends Card {
    public SkillCard(String name, String description, int cost, CardRarity rarity, CardGrave cardGrave) {
        super(name, description, cost, rarity, cardGrave, CardType.SKILL);
    }

    @Override
    public abstract void play(GameContext gameContext);
}