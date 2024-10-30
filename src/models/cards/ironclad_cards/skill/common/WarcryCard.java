package models.cards.ironclad_cards.skill.common;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.player.player_structure.Player;

public class WarcryCard extends SkillCard{


        public WarcryCard() {
            super("Warcry", "Draw 1 card. Put a card from your hand onto the top of your draw pile.Exhaust.", 0, CardRarity.COMMON, CardGrave.EXHAUST);
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();
            BattleDeck battleDeck = gameContext.getBattleDeck();

            battleDeck.drawCard(1);
            //TODO Put a card from your hand onto the top of your draw pile

            player.decreaseCurrentEnergy(getCost());
        }

        @Override
        public String toString() {
            return "Warcry(Draw 1 Card - etc.)";
        }
}
