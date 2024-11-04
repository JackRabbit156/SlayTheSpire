package models.enemy;

import models.GameContext;

import java.util.Random;

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
