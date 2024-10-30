package models.cards.ironclad_cards.skill.common;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.player.player_structure.Player;

import java.util.List;

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

            player.loseEnergy(getCost());
        }

        @Override
        public String toString() {
            return "Shrug It Off(8 Block - Draw 1 Card)";
        }

}
