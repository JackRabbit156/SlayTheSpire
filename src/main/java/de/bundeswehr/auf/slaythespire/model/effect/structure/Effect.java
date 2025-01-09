package de.bundeswehr.auf.slaythespire.model.effect.structure;

import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;

import java.util.Objects;

public abstract class Effect {

    private final String description;
    private final EffectTrigger effectTrigger;
    private String imagePath;
    private final String name;
    private final StackingBehaviour stackingBehaviour;

    public Effect(String name, String description, EffectTrigger effectTrigger, StackingBehaviour stackingBehaviour) {
        this.description = description;
        this.name = name;
        this.effectTrigger = effectTrigger;
        this.stackingBehaviour = stackingBehaviour;
    }

    /**
     * @param gameContext current {@link GameContext} to refine the application of this effects
     */
    public abstract void apply(GameContext gameContext, Entity target);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Effect effect = (Effect) o;
        return Objects.equals(description, effect.description) &&
                effectTrigger == effect.effectTrigger &&
                Objects.equals(imagePath, effect.imagePath) &&
                Objects.equals(name, effect.name) &&
                stackingBehaviour == effect.stackingBehaviour;
    }

    public String getDescription() {
        return description;
    }

    public EffectTrigger getEffectTrigger() {
        return effectTrigger;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public StackingBehaviour getStackingBehaviour() {
        return stackingBehaviour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, effectTrigger, imagePath, name, stackingBehaviour);
    }
}
