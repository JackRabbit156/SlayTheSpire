package de.bundeswehr.auf.slaythespire.model.effect.buff;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Buff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;

public class MetallicizeBuff extends Buff {

    public MetallicizeBuff() {
        super("Metallicize", "At the end of your turn, gain X Block.", EffectTrigger.END_OF_TURN, StackingBehaviour.INTENSITY);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void apply(GameContext gameContext, Entity target) {
        target.gainBlock(target.getEffectCounter(new MetallicizeBuff()));
    }

}
