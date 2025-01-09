package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;

/**
 * Clothesline Karte.
 * @author OF Daniel Willig
 */
public class ClotheslineCard extends AttackCard {

    /**
     * Constructor ClotheslineCard
     */
    public ClotheslineCard() {
        super("Clothesline", "Deal 12 damage. Apply 2 Weak.", 2, 12, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);

        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.addEffect(new WeakDebuff(), 2);
    }
}
