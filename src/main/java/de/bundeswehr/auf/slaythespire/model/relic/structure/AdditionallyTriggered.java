package de.bundeswehr.auf.slaythespire.model.relic.structure;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardTrigger;

public interface AdditionallyTriggered {

    /**
     * Was passiert wenn getriggert wird
     *
     * @param gameContext der gameContext
     */
    void onTrigger(GameContext gameContext);

    RelicTrigger getAdditionalTrigger();

}
