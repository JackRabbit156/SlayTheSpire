package models.card.ironclad_cards.power.uncommon;

import helper.PathAssistent;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.CardTrigger;
import models.card.card_structure.PowerCard;
import models.battle.BattleDeck;
import models.battle.GameContext;
import models.player.player_structure.Player;

/**
 * Die Rupture Karte.
 *
 * @author OF Daniel Willig
 */
public class RuptureCard extends PowerCard {
    /**
     * Constructor Rupture card.
     */
    public RuptureCard() {
        super("Rupture", "Whenever you lose HP from a card, gain 1 Strength.", 1, CardRarity.UNCOMMON, CardGrave.NONE, CardTrigger.LOSE_HP_CARD);
        setImagePath(new PathAssistent().toPath(this));
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
