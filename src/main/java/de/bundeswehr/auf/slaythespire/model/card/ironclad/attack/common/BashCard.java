package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.VulnerableDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;

/**
 * Bash Karte.
 * @author OF Daniel Willig
 */
public class BashCard extends AttackCard {

    /**
     * Constructor BashCard
     */
    public BashCard() {
        super("Bash", "Deal 2 Damage. Apply 2 Vulnerable.", 2, 8, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);

        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.addEffect(new VulnerableDebuff(), 2);
    }

}