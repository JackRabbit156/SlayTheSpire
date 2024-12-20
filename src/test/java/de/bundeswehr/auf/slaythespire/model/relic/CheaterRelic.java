package de.bundeswehr.auf.slaythespire.model.relic;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.potion.CheaterPotion;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicRarity;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.relic.structure.StarterTypeRelic;

public class CheaterRelic extends StarterTypeRelic {

    public CheaterRelic() {
        super("Cheater Relic", "Grants you a cheater Potion everytime you use a Potion.",
            RelicRarity.SPECIAL, PlayerType.IRONCLAD, RelicTrigger.PLAY_POTION);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.addPotion(new CheaterPotion());
    }

}