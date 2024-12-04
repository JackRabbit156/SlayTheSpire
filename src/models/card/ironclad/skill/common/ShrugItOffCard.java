package models.card.ironclad.skill.common;

import helper.PathAssistent;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.SkillCard;
import models.battle.BattleDeck;
import models.battle.GameContext;
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
