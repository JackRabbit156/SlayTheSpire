package models.potion;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;

public class EnergyPotion extends PotionCard {

    public EnergyPotion() {
        super("Energy Potion", "Gain 2 Energy.", 0, CardRarity.POTION, CardGrave.POTION);
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseCurrentEnergy(2);
    }
}
