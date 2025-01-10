package de.bundeswehr.auf.slaythespire.model.settings.structure;

import java.util.Random;

public abstract class DifficultyLevel {

    private static final Random rnd = new Random();

    private final int amount;
    private final int attackPercentage;
    private final int fourEnemies;
    private final double goldFactor;
    private final double potionChance;
    private final int threeEnemies;
    private final int twoEnemies;

    DifficultyLevel(int attackPercentage, int fourEnemies, int threeEnemies, int twoEnemies, double goldFactor, int amount, double potionChance) {
        this.attackPercentage = attackPercentage;
        this.fourEnemies = fourEnemies;
        this.threeEnemies = threeEnemies;
        this.twoEnemies = twoEnemies;
        this.goldFactor = goldFactor;
        this.amount = amount;
        this.potionChance = potionChance;
    }

    DifficultyLevel(int attackPercentage, double goldFactor, int amount, double potionChance) {
        this.attackPercentage = attackPercentage;
        this.fourEnemies = -1;
        this.threeEnemies = -1;
        this.twoEnemies = -1;
        this.goldFactor = goldFactor;
        this.amount = amount;
        this.potionChance = potionChance;
    }

    /**
     * Anzahl an möglichen Karten (beim Loot)
     */
    public int getCardAmount() {
        return amount;
    }

    public int getAttackPercentage() {
        return attackPercentage;
    }

    public int modifyDamage(int damage) {
        return damage;
    }

    public int modifyGold(int gold) {
        return (int) (gold * this.goldFactor);
    }

    public int modifyHealth(int hp) {
        return hp;
    }

    public int getNumberOfMinionsElite(int handicap) {
        return getNumberOfEnemies() - handicap;
    }

    public int getNumberOfMinionsBoss(int max) {
        return Math.min(getNumberOfEnemies(), max);
    }

    public int getNumberOfEnemies() {
        int randomNumberOfEnemies = rnd.nextInt(100) + 1;
        int numberOfEnemies = 1;
        if (fourEnemies >= randomNumberOfEnemies) {
            numberOfEnemies = 4;
        }
        else if (threeEnemies >= randomNumberOfEnemies) {
            numberOfEnemies = 3;
        }
        else if (twoEnemies >= randomNumberOfEnemies) {
            numberOfEnemies = 2;
        }
        return numberOfEnemies;
    }

    public double getPotionChance() {
        return potionChance;
    }

    public String name() {
        return getClass().getSimpleName();
    }

}
