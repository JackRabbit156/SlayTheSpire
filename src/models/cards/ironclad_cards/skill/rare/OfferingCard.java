package models.cards.ironclad_cards.skill.rare;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.player.player_structure.Player;

public class OfferingCard extends SkillCard{


        public OfferingCard() {
            super("Offering", "Lose 6 HP. Gain 2 Energy. Draw 3 cards. Exhaust.", 0, CardRarity.RARE, CardGrave.EXHAUST);
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();
            BattleDeck battleDeck = gameContext.getBattleDeck();

            player.decreaseCurrentHealth(6);
            player.increaseCurrentEnergy(2);
            battleDeck.drawCard(3);

            player.decreaseCurrentEnergy(getCost());
        }

        @Override
        public String toString() {
            return "Offering(Lose 6 HP - Gain 2 Energy - Draw 3 cards)";
        }

}
