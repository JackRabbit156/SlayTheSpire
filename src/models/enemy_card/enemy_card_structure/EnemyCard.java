package models.enemy_card.enemy_card_structure;

import models.battle.GameContext;
import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.CardType;
import models.enemy.Enemy;
import models.enemy.act_one.boss.TheGuardianBoss;

public abstract class EnemyCard extends Card {
    public EnemyCard(String name, String description) {
        super(name, description, 0, CardRarity.ENEMY, CardGrave.ENEMY, CardType.ENEMY);
    }


    public abstract void play(GameContext gameContext, Enemy enemy);


    @Override
    public String toString() {
        return "";
    }

}
