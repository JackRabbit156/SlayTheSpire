package de.bundeswehr.auf.slaythespire.events;

import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Das Effect Event
 * @author L Frank Rieger
 */
public class EffectEvent {

    private final Entity entity;
    private final Effect effect;
    private final int value;

    public EffectEvent(Entity entity, Effect effect, int value) {
        this.entity = entity;
        this.effect = effect;
        this.value = value;
    }

    public Effect getEffect() {
        return effect;
    }

    public Entity getEntity() {
        return entity;
    }

    public int getValue() {
        return value;
    }

}
