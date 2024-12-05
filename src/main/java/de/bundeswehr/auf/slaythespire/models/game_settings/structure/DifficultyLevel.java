package de.bundeswehr.auf.slaythespire.models.game_settings.structure;

import java.util.Random;

public enum DifficultyLevel {

    SUPER_EASY(25, 0, 0, 0, 1.5, 6, 1.0),
    EASY(50, 0, 25, 50, 1.5, 5, 0.8),
    NORMAL(75, 25, 50, 75, 1.0, 3, 0.5),
    HARD(90, 50, 75, 100, 0.5, 1, 0.1),
    IMPOSSIBLE(100, 100, 100, 100, 0.1, 1, 0.0);

    private static final Random rnd = new Random();

    private final int attackPercentage;
    private final int fourEnemies;
    private final int threeEnemies;
    private final int twoEnemies;
    private final double goldFactor;
    private final int amount;
    private final double potionChance;

    DifficultyLevel(int attackPercentage, int fourEnemies, int threeEnemies, int twoEnemies, double goldFactor, int amount, double potionChance) {
        this.attackPercentage = attackPercentage;
        this.fourEnemies = fourEnemies;
        this.threeEnemies = threeEnemies;
        this.twoEnemies = twoEnemies;
        this.goldFactor = goldFactor;
        this.amount = amount;
        this.potionChance = potionChance;
    }

    public int get2Enemies() {
        return twoEnemies;
    }

    public int get3Enemies() {
        return threeEnemies;
    }

    public int get4Enemies() {
        return fourEnemies;
    }

    public int getAttackPercentage() {
        return attackPercentage;
    }

    public int getGold(int gold) {
        return (int) (gold * this.goldFactor);
    }

    public int getAmount() {
        return amount;
    }

    public double getPotionChance() {
        return potionChance;
    }

    public int getNumberOfEnemies() {
        int randomNumberOfEnemies = rnd.nextInt(100) + 1;
        int numberOfEnemies = 1;
        if (get4Enemies() >= randomNumberOfEnemies) {
            numberOfEnemies = 4;
        }
        else if (get3Enemies() >= randomNumberOfEnemies) {
            numberOfEnemies = 3;
        }
        else if (get2Enemies() >= randomNumberOfEnemies) {
            numberOfEnemies = 2;
        }
        return numberOfEnemies;
    }

}
