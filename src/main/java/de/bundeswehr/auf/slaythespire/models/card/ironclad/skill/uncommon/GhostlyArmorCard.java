package de.bundeswehr.auf.slaythespire.models.card.ironclad.skill.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardGrave;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardRarity;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.SkillCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

/**
 * Die Ghostly armor Karte.
 *
 * @author OF Daniel Willig
 */
public class GhostlyArmorCard extends SkillCard{


    /**
     * Constructor Ghostly armor card.
     */
    public GhostlyArmorCard() {
            super("Ghostly Armor", "Exhaust. Gain 10 Block.", 1, CardRarity.UNCOMMON, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();

            player.increaseBlock(10);

            player.decreaseCurrentEnergy(getCost());
        }
}
