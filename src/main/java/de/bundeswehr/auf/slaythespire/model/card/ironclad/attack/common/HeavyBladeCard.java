package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.Map;

/**
 * Die Heavy blade Karte.
 * @author OF Daniel Willig
 */
public class HeavyBladeCard extends AttackCard {

    /**
     * Constructor HeavyBladeCard
     */
    public HeavyBladeCard() {
        super("Heavy Blade", "Deal 14 damage. Strength affects this card 3 times.", 2, 14, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        Map<Effect, Integer> effects = player.getEffects();
        StrengthBuff key = new StrengthBuff();
        effects.put(key, effects.getOrDefault(key, 0) * 3);
        super.play(gameContext);
        effects.put(key, effects.getOrDefault(key, 0) / 3);
    }

}
