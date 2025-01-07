package de.bundeswehr.auf.slaythespire.model.card;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;

public class CheaterHealCard extends SkillCard {

    public CheaterHealCard() {
        super("Cheater Heal Card", "Heals 50 HP. For free.", 0, CardRarity.SPECIAL, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        gameContext.getPlayer().increaseCurrentHealth(50);
    }

}
