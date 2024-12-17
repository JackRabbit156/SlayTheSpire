package de.bundeswehr.auf.slaythespire.model.relic;

import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;

public enum Relics {

    BURNING_BLOOD_RELIC(BurningBloodRelic.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public BurningBloodRelic create() {
            return new BurningBloodRelic();
        }
    },
    RING_OF_THE_SNAKE_RELIC(RingOfTheSnakeRelic.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public RingOfTheSnakeRelic create() {
            return new RingOfTheSnakeRelic();
        }
    };

    private final String name;

    Relics(String name) {
        this.name = name;
    }

    public abstract <C extends Relic> C create();

    @Override
    public String toString() {
        return name;
    }

}
