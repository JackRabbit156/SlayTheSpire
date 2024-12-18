package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Carnage Karte.
 *
 * @author OF Daniel Willig
 */
public class CarnageCard extends AttackCard {

    /**
     * Constructor Carnage card.
     */
    public CarnageCard() {
        super("Carnage", "Deal 20 damage.", 2, 20, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

}
