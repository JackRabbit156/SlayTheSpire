package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.VulnerableDebuff;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;

/**
 * Die Uppercut Karte.
 *
 * @author OF Daniel Willig
 */
public class UppercutCard extends AttackCard {

    /**
     * Constructor Uppercut card.
     */
    public UppercutCard() {
        super("Uppercut", "Deal 13 damage. Apply 1 Weak. Apply 1 Vulnerable.", 2, 13, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);

        gameContext.getSelectedEnemy().addEffect(new WeakDebuff(), 1);
        gameContext.getSelectedEnemy().addEffect(new VulnerableDebuff(), 1);
    }

}
