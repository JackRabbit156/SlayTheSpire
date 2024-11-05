package models.cards.ironclad_cards.skill.uncommon;

import models.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.player.player_structure.Player;

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
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();

            player.increaseBlock(player.getBlock());

            player.decreaseCurrentEnergy(getCost());
        }

        @Override
        public String toString() {
            return "Entrench(2x Block)";
        }

}
