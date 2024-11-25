package models.enemy_card.enemy_card_structure;

import models.battle.GameContext;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.CardType;
import models.enemy.Enemy;

import java.util.Random;

public abstract class EnemyCard {
    private String name;
    private String description;
    private String iconText;

    private String imagePath;
    public EnemyCard(String name, String description, String iconText) {
        this.name = name;
        this.description = description;
        this.iconText = iconText;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public abstract void playEnemy(GameContext gameContext, Enemy enemy);


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIconText() {
        return iconText;
    }
}