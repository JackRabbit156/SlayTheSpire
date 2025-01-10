package de.bundeswehr.auf.slaythespire.model.card.structure;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.battle.Playable;

public abstract class UnplayableCard extends StatusCard {

    private final Playable notPlayable;

    public UnplayableCard(String name, String description, CardGrave cardGrave) {
        super(name, description, -1, cardGrave);
        notPlayable = new Playable(name + " is Unplayable", "Card cannot be played.");
    }

    @Override
    public void play(GameContext gameContext) {
        throw new IllegalStateException(getName() + " is Unplayable");
    }

    @Override
    public Playable isPlayable(GameContext gameContext) {
        return notPlayable;
    }

}
