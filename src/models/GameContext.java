package models;

import models.enemy.Enemy;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.player.player_structure.Player;

import java.util.List;

public class GameContext {
    private final Player player;
    private final List<Enemy> enemies;
    private final BattleDeck battleDeck;

    public GameContext(Player player, List<Enemy> enemies, BattleDeck battleDeck){
        this.player = player;
        this.enemies = enemies;
        this.battleDeck = battleDeck;
    }

    public Player getPlayer(){
        return player;
    }

    public List<Enemy> getEnemies(){
        return enemies;
    }

    public BattleDeck getBattleDeck() {
        return battleDeck;
    }
}