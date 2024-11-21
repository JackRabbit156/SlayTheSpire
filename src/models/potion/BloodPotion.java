package models.potion;

import helper.PathAssistent;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.battle.GameContext;
import models.card.card_structure.CardType;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;

public class BloodPotion extends PotionCard {

    public BloodPotion() {
        super("Blood Potion", "Heal for 20% of your Max HP.", 0, CardRarity.COMMON, CardGrave.POTION, CardType.SKILL);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        System.out.println("dfikghsdfhio");
        Player player = gameContext.getPlayer();
        player.increaseCurrentHealth((int) (player.getMaxHealth() * 0.2));
    }
}
