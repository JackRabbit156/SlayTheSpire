package de.bundeswehr.auf.slaythespire.model.settings.structure;

import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;

public interface Resetable {

    void reset();

    RelicTrigger getResetTrigger();

}
