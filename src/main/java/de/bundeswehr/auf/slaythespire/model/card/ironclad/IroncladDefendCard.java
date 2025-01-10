package de.bundeswehr.auf.slaythespire.model.card.ironclad;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.*;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

public class IroncladDefendCard extends SkillCard {

    public IroncladDefendCard() {
        super("Defend", "Gain 5 Block.", 1, CardRarity.BASIC, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.gainBlock(5);

        player.decreaseCurrentEnergy(getCost());
    }

}