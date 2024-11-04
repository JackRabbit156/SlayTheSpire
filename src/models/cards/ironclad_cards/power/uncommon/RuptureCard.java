package models.cards.ironclad_cards.power.uncommon;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.CardTrigger;
import models.cards.card_structure.PowerCard;
import models.player.player_structure.Player;

public class RuptureCard extends PowerCard {
    public RuptureCard() {
        super("Rupture", "Whenever you lose HP from a card, gain 1 Strength.", 1, CardRarity.UNCOMMON, CardGrave.NONE, CardTrigger.LOSE_HP_CARD);
    }


    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();

        battleDeck.addPowerCards(new RuptureCard());

        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public void ability(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        //TODO gain Strength
    }
}
