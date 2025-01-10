package de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Ghostly armor Karte.
 *
 * @author OF Daniel Willig
 */
public class GhostlyArmorCard extends SkillCard {

    /**
     * Constructor Ghostly armor card.
     */
    public GhostlyArmorCard() {
        super("Ghostly Armor", "Ethereal. Gain 10 Block.", 1, CardRarity.UNCOMMON, CardGrave.ETHEREAL);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        player.gainBlock(10);

        player.decreaseCurrentEnergy(getCost());
    }

}
