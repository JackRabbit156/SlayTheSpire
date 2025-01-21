package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
import javafx.scene.layout.HBox;

import java.util.HashSet;
import java.util.Map;

public class EffectBarLayout extends HBox {

    private final Entity entity;

    public EffectBarLayout(Entity entity) {
        this.entity = entity;
        setMinHeight(30);
        update();
    }

    public void update() {
        getChildren().clear();
        for (Map.Entry<Effect, Integer> entry : new HashSet<>(entity.getEffects().entrySet())) {
            if (entry.getValue() > 0) {
                getChildren().add(new EffectLayout(entry.getKey(), entry.getValue()));
            }
        }
    }

}
