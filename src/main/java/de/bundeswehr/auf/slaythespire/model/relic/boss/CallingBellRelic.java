package de.bundeswehr.auf.slaythespire.model.relic.boss;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.curse.CurseOfTheBellCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.RelicFactory;
import de.bundeswehr.auf.slaythespire.model.relic.structure.BossTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public class CallingBellRelic extends BossTypeRelic {

    public CallingBellRelic() {
        super("Calling Bell", "Upon pickup, obtain a unique Curse and 3 relics.",
                RelicTrigger.PICKUP);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.addCardToDeck(new CurseOfTheBellCard());

        RelicFactory relicFactory = new RelicFactory(player);
        player.addRelic(relicFactory.generateRelicForLoot(RelicRarity.COMMON));
        player.addRelic(relicFactory.generateRelicForLoot(RelicRarity.UNCOMMON));
        player.addRelic(relicFactory.generateRelicForLoot(RelicRarity.RARE));
    }

}
