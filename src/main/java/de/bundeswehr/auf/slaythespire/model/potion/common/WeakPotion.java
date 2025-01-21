package de.bundeswehr.auf.slaythespire.model.potion.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.VulnerableDebuff;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.AttackPotion;

/**
 * Die Weak potion.
 *
 * @author L Frank Rieger
 */
public class WeakPotion extends AttackPotion {

    public WeakPotion() {
        super("Weak Potion", "Apply 3 Weak.", 0, CardRarity.COMMON);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.addEffect(new WeakDebuff(), 3);
    }

}
