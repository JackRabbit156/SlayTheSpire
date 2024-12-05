package de.bundeswehr.auf.slaythespire.models.card.ironclad.skill.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardGrave;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardRarity;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.SkillCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

/**
 * Die Entrench Karte.
 *
 * @author OF Daniel Willig
 */
public class EntrenchCard extends SkillCard{


    /**
     * Constructor Entrench card.
     */
    public EntrenchCard() {
            super("Entrench", "Double your Block.", 2, CardRarity.UNCOMMON, CardGrave.DISCARD);
    setImagePath(new PathAssistent().toPath(this));
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();

            player.increaseBlock(player.getBlock());

            player.decreaseCurrentEnergy(getCost());
        }
}
