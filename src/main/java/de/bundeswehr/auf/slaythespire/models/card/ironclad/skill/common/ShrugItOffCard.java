package de.bundeswehr.auf.slaythespire.models.card.ironclad.skill.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardGrave;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardRarity;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.SkillCard;
import de.bundeswehr.auf.slaythespire.models.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

/**
 * Die Shrug it off Karte.
 *
 * @author OF Daniel Willig
 */
public class ShrugItOffCard extends SkillCard{


    /**
     * Constructor Shrug it off card.
     */
    public ShrugItOffCard() {
            super("Shrug It Off", "Gain 8 Block. Draw 1 card.", 1, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();
            BattleDeck battleDeck = gameContext.getBattleDeck();

            player.increaseBlock(8);
            battleDeck.drawCard(1);

            player.decreaseCurrentEnergy(getCost());
        }
}
