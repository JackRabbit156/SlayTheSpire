package models.enemy;

import models.GameContext;
import models.game_settings.structure.DifficultyLevel;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Enemy {
    private String name;
    private int currentHealth;
    private int maxHealth;

    private int block;

    public Enemy(String name, int lowestMaxHealthPossible, int highestMaxHealthPossible) {
        this.name = name;

        this.maxHealth = generateMaxHealth(lowestMaxHealthPossible, highestMaxHealthPossible);
        this.currentHealth = maxHealth;
    }

    public void action(GameContext gameContext) {
        DifficultyLevel difficulty = gameContext.getDifficultyLevel();
        int randomNumber = (new Random().nextInt(100) + 1);
        int attackPercentage = 100; //should be normal

        if (difficulty.equals(DifficultyLevel.SUPEREASY)) {
            attackPercentage = 50;
        }
        else if (difficulty.equals(DifficultyLevel.EASY)) {
            attackPercentage = 75;
        }
        else if (difficulty.equals(DifficultyLevel.HARD)) {
            System.out.println("hard not yet implemented");
            //TODO hard
        }
        else if (difficulty.equals(DifficultyLevel.IMPOSSIBLE)) {
            System.out.println("impossible not yet implemented");
            //TODO impossible
        }

        if (attackPercentage >= randomNumber) {
            attack(gameContext);
        }
        else {
            System.out.println(doNothing());
        }
    }

    protected String doNothing(){
        ArrayList<String> wittyBanterList = new ArrayList<>();
        Scanner fileScanner = new Scanner(Enemy.class.getResourceAsStream("wittybanter.txt"));
        while (fileScanner.hasNext())
        {
            wittyBanterList.add(fileScanner.nextLine());
        }

        fileScanner.close();


        return "\u001B[3m" + "\u001B[36m" + wittyBanterList.get(Math.abs(new Random().nextInt() % wittyBanterList.size())) + "\u001B[0m";
    }


    public abstract void attack(GameContext gameContext);

    private int generateMaxHealth(int lowestMaxHealthPossible, int highestMaxHealthPossible){
        int difference = highestMaxHealthPossible - lowestMaxHealthPossible;

        Random randi = new Random();

        int hp = randi.nextInt(difference+1);

        return lowestMaxHealthPossible + hp;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void takeDamage(int damage) {
        if(block == 0){
            currentHealth -= damage;
            if (currentHealth < 0) currentHealth = 0;
        } else {
            block -= damage;
            if (block < 0) {
                currentHealth += block;
                block = 0;
            }
        }

        if (currentHealth < 0)
            currentHealth = 0;
    }

    public void setBlock(int block){
        this.block = block;
    }

    public int getBlock(){
        return block;
    }

    public void addBlock(int block){
        this.block += block;
    }
}
