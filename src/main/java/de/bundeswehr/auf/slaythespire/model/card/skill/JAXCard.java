package de.bundeswehr.auf.slaythespire.model.card.skill;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.StatusCard;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

public class JAXCard extends SkillCard {

    public JAXCard() {
        super("J.A.X.", "Lose 3 HP. Gain 2 Strength.", 0, CardRarity.SPECIAL, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        gameContext.setAttackContext(new AttackContext(null, player, 3, this));
        player.takeDamage(gameContext);
        player.addEffect(new StrengthBuff(), 2);
    }

}
