package models.card.ironclad_cards.power.rare;

import helper.PathAssistent;
import models.battle.BattleDeck;
import models.battle.GameContext;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.CardTrigger;
import models.card.card_structure.PowerCard;
import models.player.player_structure.Player;

/**
 * Die Berserk Karte.
 *
 * @author OF Daniel Willig
 */
public class BerserkCard extends PowerCard {
    /**
     * Constructor Berserk card.
     */
    public BerserkCard() {
        super("Berserk", "Gain 2 Vulnerable. At the start of your turn, gain 1 Energy.", 0, CardRarity.RARE, CardGrave.NONE, CardTrigger.PLAYER_BOT);
        setImagePath(new PathAssistent().toPath(this));
    }


    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.addPowerCards(new BerserkCard());

        //TODO Debuff Vulnerable

        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public void ability(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        player.increaseCurrentEnergy(1);
    }
}
