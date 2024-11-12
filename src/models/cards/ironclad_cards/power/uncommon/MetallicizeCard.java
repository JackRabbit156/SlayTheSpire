package models.cards.ironclad_cards.power.uncommon;

import helper.PathAssistent;
import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.CardTrigger;
import models.cards.card_structure.PowerCard;
import models.player.player_structure.Player;

/**
 * Die Metallicize Karte.
 *
 * @author OF Daniel Willig
 */
public class MetallicizeCard extends PowerCard {
    /**
     * Constructor Metallicize card.
     */
    public MetallicizeCard() {
        super("Metallicize", "At the end of your turn, gain 3 Block.", 1, CardRarity.UNCOMMON, CardGrave.NONE, CardTrigger.PLAYER_EOT);
        setImagePath(new PathAssistent().toPath(this));
    }


    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();

        battleDeck.addPowerCards(new MetallicizeCard());

        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public void ability(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        player.increaseBlock(3);
    }
}
