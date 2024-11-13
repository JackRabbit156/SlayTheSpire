package models.potion;

import helper.PathAssistent;
import models.GameContext;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;

public class EnergyPotion extends PotionCard {

    public EnergyPotion() {
        super("Energy Potion", "Gain 2 Energy.", 0, CardRarity.POTION, CardGrave.POTION);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseCurrentEnergy(2);
    }
}
