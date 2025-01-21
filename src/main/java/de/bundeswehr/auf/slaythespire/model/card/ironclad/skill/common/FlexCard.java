package de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.effect.player.debuff.StrengthDownDebuffPlayer;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Flex Karte.
 *
 * @author L Frank Rieger
 */
public class FlexCard extends SkillCard {

    public FlexCard() {
        super("Flex", "Gain 2 Strength. At the end of your turn, lose 2 Strength.", 0, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.addEffect(new StrengthBuff(), 2);
        player.addEffect(new StrengthDownDebuffPlayer(), 2);

        player.decreaseCurrentEnergy(getCost());
    }

}
