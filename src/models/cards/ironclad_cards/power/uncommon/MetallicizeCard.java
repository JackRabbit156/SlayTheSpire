package models.cards.ironclad_cards.power.uncommon;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.CardTrigger;
import models.cards.card_structure.PowerCard;
import models.player.player_structure.Player;

public class MetallicizeCard extends PowerCard {
    public MetallicizeCard() {
        super("Metallicize", "At the end of your turn, gain 3 Block.", 1, CardRarity.UNCOMMON, CardGrave.NONE, CardTrigger.PLAYER_EOT);
    }


    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();

        battleDeck.getCurrentPowerCards().add(new MetallicizeCard());

        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public void ability(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        player.increaseBlock(3);
    }
}
