package de.bundeswehr.auf.slaythespire.model.battle;

import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;

public class EffectContext {

    private final Effect effect;
    private final Entity source;
    private final Entity target;

    public EffectContext(Entity source, Entity target, Effect effect) {
        this.source = source;
        this.target = target;
        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }

    public Entity getSource() {
        return source;
    }

    public Entity getTarget() {
        return target;
    }

}
