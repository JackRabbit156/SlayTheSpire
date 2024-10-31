package models.cards.ironclad_cards.power.uncommon;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.CardTrigger;
import models.cards.card_structure.PowerCard;
import models.player.player_structure.Player;

public class InflameCard extends PowerCard {
    public InflameCard() {
        super("Inflame", "Gain 2 Strength.", 1, CardRarity.UNCOMMON, CardGrave.NONE, CardTrigger.ALWAYS);
    }


    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();

        battleDeck.addPowerCards(new InflameCard());

        //TODO gain 2 Strength

        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public void ability(GameContext gameContext) {
        // CardTrigger.ALWAYS does not need an ability.
    }
}
