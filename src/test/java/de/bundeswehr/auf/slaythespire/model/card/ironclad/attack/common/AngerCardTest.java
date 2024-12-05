package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.controller.BattleController;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy.act_one.boss.SlimeBoss;
import de.bundeswehr.auf.slaythespire.model.map.field.FieldEnum;
import de.bundeswehr.auf.slaythespire.model.player.IroncladPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class AngerCardTest {
    AngerCard angerCard = new AngerCard();

    GameContext gameContext;
    BattleController battleController;


    private void initGame() {
        IroncladPlayer ironcladPlayer = new IroncladPlayer(null);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new SlimeBoss());
        List<Card> deck = new ArrayList<>();
        deck.add(angerCard);
        BattleDeck battleDeck = new BattleDeck(deck);
        gameContext = new GameContext(ironcladPlayer, enemies, battleDeck);

        battleController = new BattleController(ironcladPlayer,enemies, FieldEnum.BOSSFIELD); //TODO error an der stelle
    }



    @Test
    void playEnemyTakesCorrectAmountOfDamage() {
        initGame();
        Enemy enemy = gameContext.getEnemies().get(0);
        gameContext.setSelectedEnemy(enemy);



        int oldHealth = gameContext.getEnemies().get(0).getHealth();

        angerCard.play(gameContext);

        int newHealth = gameContext.getEnemies().get(0).getHealth();


        int expected = 6;
        int actual = oldHealth - newHealth;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void dealDamage() {
        int expected = 6;
        int actual = angerCard.dealDamage();

        Assertions.assertEquals(expected, actual);
    }
}