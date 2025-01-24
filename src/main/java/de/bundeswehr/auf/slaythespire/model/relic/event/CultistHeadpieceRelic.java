package de.bundeswehr.auf.slaythespire.model.relic.event;

import de.bundeswehr.auf.slaythespire.gui.components.animation.BanterText;
import de.bundeswehr.auf.slaythespire.helper.Animate;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.EventTypeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public class CultistHeadpieceRelic extends EventTypeRelic {

    public CultistHeadpieceRelic() {
        super("Cultist Headpiece", "You feel more talkative.", RelicTrigger.START_OF_COMBAT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void activate(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.scream("CAW! CAAAW");
    }

}