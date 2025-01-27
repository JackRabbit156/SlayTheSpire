package de.bundeswehr.auf.slaythespire.model.relic.boss;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.PotionFactory;
import de.bundeswehr.auf.slaythespire.model.relic.structure.BossTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

import java.util.List;

public class TinyHouseRelic extends BossTypeRelic {

    public TinyHouseRelic() {
        super("Tiny House", "Obtain 1 potion. Gain 50 Gold. Raise your Max HP by 5. Obtain 1 card.",
                RelicTrigger.PICKUP);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        if (player.getPotions().size() < 3) {
            player.addPotion(PotionFactory.generatePotion());
        }

        player.gainGold(50);

        player.gainMaxHp(5);

        DeckFactory deckFactory = new DeckFactory(player, 1);
        List<Card> cards = deckFactory.init(false);
        player.addCardToDeck(cards.get(0));
    }

}
