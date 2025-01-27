package de.bundeswehr.auf.slaythespire.model.relic.boss.ironclad;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.potion.PotionFactory;
import de.bundeswehr.auf.slaythespire.model.relic.structure.BossTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.PlayerTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

import java.util.List;

public class BlackBloodRelic extends PlayerTypeRelic {

    public BlackBloodRelic() {
        super("Black Blood", "At the end of combat, heal 12 HP.",
                RelicRarity.BOSS, PlayerType.IRONCLAD, RelicTrigger.END_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.heal(12);
    }

}
