package de.bundeswehr.auf.slaythespire.model.potion.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.effect.buff.RitualBuff;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.SkillPotion;

/**
 * Die Cultist potion.
 *
 * @author L Frank Rieger
 */
public class CultistPotion extends SkillPotion {

    public CultistPotion() {
        super("Cultist Potion", "Gain 1 Ritual.", CardRarity.RARE);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.addEffect(new RitualBuff(), 1);
    }

}
