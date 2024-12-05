package de.bundeswehr.auf.slaythespire.models.card.ironclad.power.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardGrave;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardRarity;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardTrigger;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.PowerCard;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

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
        super("Berserk", "At the start of your turn, gain 1 Energy.", 0, CardRarity.RARE, CardGrave.NONE, CardTrigger.PLAYER_BOT);
        setImagePath(new PathAssistent().toPath(this));
    }


    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.addPowerCards(new BerserkCard());

        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public void ability(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        player.increaseCurrentEnergy(1);
    }
}
