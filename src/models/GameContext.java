package models;

import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

public class GameContext {
    private Player player;
    private List<Enemy> enemies;

    public GameContext(Player player, List<Enemy> enemies){
        this.player = player;
        this.enemies = enemies;
    }

    public Player getPlayer(){
        return player;
    }

    public List<Enemy> getEnemies(){
        return enemies;
    }
}