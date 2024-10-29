package models.enemy;

public class Enemy {
    private String name;
    private int health;
    private int maxHealth;

    public Enemy(String name, int health) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int attack() {
        int damage = 5; // example damage
        return damage;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }
}
