package models.cards.ironclad_cards.skill.common;

import helper.PathAssistent;
import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.player.player_structure.Player;

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
