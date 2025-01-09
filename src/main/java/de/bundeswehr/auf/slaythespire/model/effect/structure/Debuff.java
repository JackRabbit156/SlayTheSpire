package de.bundeswehr.auf.slaythespire.model.effect.structure;

public abstract class Debuff extends Effect {

    public Debuff(String name, String description, EffectTrigger effectTrigger, StackingBehaviour stackingBehaviour) {
        super(name, description, effectTrigger, stackingBehaviour);
    }

}
