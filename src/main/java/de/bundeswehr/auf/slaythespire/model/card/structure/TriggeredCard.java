package de.bundeswehr.auf.slaythespire.model.card.structure;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;

public interface TriggeredCard {

    /**
     * Was passiert wenn die Karte getriggert wird
     *
     * @param gameContext der gameContext
     */
    void onTrigger(GameContext gameContext);

    CardTrigger getTrigger();

}
