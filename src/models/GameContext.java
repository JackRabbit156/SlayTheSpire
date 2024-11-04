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
    private final DifficultyLevel difficultyLevel;

    public GameContext(Player player, List<Enemy> enemies, BattleDeck battleDeck, DifficultyLevel difficultyLevel){
        this.player = player;
        this.enemies = enemies;
        this.battleDeck = battleDeck;
        this.difficultyLevel = difficultyLevel;
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

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }
}