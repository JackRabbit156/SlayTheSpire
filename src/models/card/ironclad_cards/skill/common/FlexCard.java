package models.card.ironclad_cards.skill.common;

import helper.PathAssistent;
import models.BattleDeck;
import models.GameContext;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.SkillCard;
import models.player.player_structure.Player;

/**
 * Die Flex Karte.
 *
 * @author OF Daniel Willig
 */
public class FlexCard extends SkillCard{


    /**
     * Constructor Flex card.
     */
    public FlexCard() {
            super("Flex", "Gain 2 Strength. At the end of this turn, lose 2 Strength.", 0, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();
            BattleDeck battleDeck = gameContext.getBattleDeck();

            battleDeck.drawCard(1);
            //TODO 2 Strength
            //TODO -2 Strength at eot

            player.decreaseCurrentEnergy(getCost());
        }
}
