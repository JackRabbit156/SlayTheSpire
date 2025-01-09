package de.bundeswehr.auf.slaythespire.model;

import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;

import java.util.Map;

public interface Entity {

    void addDamageFactor(double factor);

    void addDamageModifier(int modifier);

    int getEffectCounter(Effect effect);

    Map<Effect, Integer> getEffects();
}
