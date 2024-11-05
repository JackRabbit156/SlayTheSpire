package models.cards.ironclad_cards.skill.common;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
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

        @Override
        public String toString() {
            return "Flex(2 Strength - etc.)";
        }
}
