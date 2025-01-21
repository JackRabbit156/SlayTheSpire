package de.bundeswehr.auf.slaythespire.model.potion.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.effect.buff.RegenerationBuff;
import de.bundeswehr.auf.slaythespire.model.effect.buff.ThornsBuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.SkillPotion;

/**
 * Die Regen potion.
 *
 * @author L Frank Rieger
 */
public class RegenPotion extends SkillPotion {

    public RegenPotion() {
        super("Regen Potion", "Gain 5 Regeneration.", CardRarity.UNCOMMON);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.addEffect(new RegenerationBuff(), 5);
    }

}
