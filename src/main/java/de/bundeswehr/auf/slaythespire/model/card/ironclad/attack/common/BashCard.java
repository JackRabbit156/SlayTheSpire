package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

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
        // TODO apply 2 Vulnerable

        super.play(gameContext);
    }

}