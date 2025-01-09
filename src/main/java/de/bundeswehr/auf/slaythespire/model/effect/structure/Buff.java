package de.bundeswehr.auf.slaythespire.model.effect.structure;

public abstract class Buff extends Effect {

    public Buff(String name, String description, EffectTrigger effectTrigger, StackingBehaviour stackingBehaviour) {
        super(name, description, effectTrigger, stackingBehaviour);
    }

}
