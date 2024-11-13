package models.card.ironclad_cards.skill.rare;

import helper.PathAssistent;
import models.BattleDeck;
import models.GameContext;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.SkillCard;
import models.player.player_structure.Player;

/**
 * Die Offering Karte.
 *
 * @author OF Daniel Willig
 */
public class OfferingCard extends SkillCard{


    /**
     * Constructor Offering card.
     */
    public OfferingCard() {
            super("Offering", "Lose 6 HP. Gain 2 Energy. Draw 3 cards. Exhaust.", 0, CardRarity.RARE, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();
            BattleDeck battleDeck = gameContext.getBattleDeck();

            player.decreaseCurrentHealth(6, true);
            player.increaseCurrentEnergy(2);
            battleDeck.drawCard(3);

            player.decreaseCurrentEnergy(getCost());
        }
}
