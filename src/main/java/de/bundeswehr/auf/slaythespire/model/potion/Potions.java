package de.bundeswehr.auf.slaythespire.model.potion;

import de.bundeswehr.auf.slaythespire.model.potion.structure.PotionCard;

public enum Potions {

    BLOCK_POTION(BlockPotion.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public BlockPotion create() {
            return new BlockPotion();
        }
    },
    BLOOD_POTION(BloodPotion.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public BloodPotion create() {
            return new BloodPotion();
        }
    },
    DISTILLED_CHAOS_POTION(DistilledChaosPotion.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public DistilledChaosPotion create() {
            return new DistilledChaosPotion();
        }
    },
    ENERGY_POTION(EnergyPotion.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public EnergyPotion create() {
            return new EnergyPotion();
        }
    },
    EXPLOSIVE_POTION(ExplosivePotion.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public ExplosivePotion create() {
            return new ExplosivePotion();
        }
    },
    FIRE_POTION(FirePotion.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public FirePotion create() {
            return new FirePotion();
        }
    },
    SWIFT_POTION(SwiftPotion.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public SwiftPotion create() {
            return new SwiftPotion();
        }
    },;

    private final String name;

    Potions(String name) {
        this.name = name;
    }

    public abstract <C extends PotionCard> C create();

    @Override
    public String toString() {
        return name;
    }

}
