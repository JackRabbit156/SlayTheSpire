package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.controller.listener.EmptyEnemyEventListener;
import de.bundeswehr.auf.slaythespire.events.EffectEvent;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.battle.EffectContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.InsultEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.relic.structure.RelicTrigger;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;

import java.util.*;

public class EnemyController extends EmptyEnemyEventListener {

    /**
     * Generiert einen Wert für den Gegner innerhalb des angegebenen Bereichs.
     *
     * @param lowest  Der niedrigste mögliche Wert.
     * @param highest Der höchste mögliche Wert.
     * @return Der generierte Wert.
     */
    public static int generateBetween(int lowest, int highest) {
        int difference = highest - lowest;
        return rnd.nextInt(difference + 1) + lowest;
    }

    private static final Random rnd = new Random();

    private final Enemy enemy;
    private final GameContext gameContext;
    private final EnemyCard insult = new InsultEnemyCard();
    private final PlayerController player;
    private final List<String> wittyBanterList = new ArrayList<>();

    public EnemyController(Enemy enemy, GameContext gameContext, PlayerController player) {
        this.enemy = enemy;
        this.gameContext = gameContext;
        this.player = player;
        try (Scanner fileScanner = new Scanner(Enemy.class.getResourceAsStream("/wittybanter.txt"))) {
            while (fileScanner.hasNext()) {
                wittyBanterList.add(fileScanner.nextLine());
            }
        }
    }

    /**
     * Eine Action ist entweder ein ganz normaler angriff oder eine Beleidigung des Spielers (genommen aus wittybanter.txt)
     *
     * @author OF Daniel Willig
     */
    public void action() {
        if (enemy.isAlive()) {
            enemy.reduceDurationEffects();
            triggerEffects(EffectTrigger.BEGIN_OF_TURN);
            if (enemy.getIntent().equals(insult)) {
                String banter = doNothing();
                LoggingAssistant.log(enemy.getName() + ": \"" + banter + "\"", Color.ITALIC, Color.CYAN);
                enemy.notifyBanter(banter);
            }
            else {
                LoggingAssistant.log(enemy.getName() + " attacking", Color.ITALIC, Color.BLUE);
                enemy.attack(gameContext);
            }
            triggerEffects(EffectTrigger.END_OF_TURN);
        }
    }

    /**
     * Wählt ine Beleidigung des Spielers (genommen aus wittybanter.txt)
     *
     * @return call of the enemy
     * @author OF Daniel Willig
     */
    private String doNothing() {
        return wittyBanterList.get(rnd.nextInt(wittyBanterList.size()));
    }

    /**
     * Berechnet die Aktion bevor sie ausgeführt wird.
     *
     * @author OF Daniel Willig
     */
    public void calculateIntent() {
        EnemyCard intent;
        DifficultyLevel difficulty = GameSettings.getDifficultyLevel();
        int randomNumber = (rnd.nextInt(100) + 1);
        if (difficulty.getAttackPercentage() >= randomNumber) {
            List<EnemyCard> enemyDeck = enemy.getEnemyDeck();
            int enemyCardToBePlayed = rnd.nextInt(enemyDeck.size());
            intent = enemyDeck.get(enemyCardToBePlayed);
        }
        else {
            intent = insult;
        }
        enemy.setIntent(intent);
    }

    public boolean isAlive() {
        return enemy.isAlive();
    }

    @Override
    public void onEffect(EffectEvent event) {
        gameContext.setEffectContext(new EffectContext(gameContext.getPlayer(), enemy, event.getEffect()));
        player.triggerRelics(RelicTrigger.EFFECT);
    }

    public void resetBlock() {
        enemy.setBlock(0);
    }

    private void triggerEffects(EffectTrigger trigger) {
        for (Effect effect : new HashSet<>(enemy.getEffects().keySet())) {
            if (effect.getEffectTrigger() == trigger) {
                effect.apply(gameContext, enemy);
            }
        }
    }

}
