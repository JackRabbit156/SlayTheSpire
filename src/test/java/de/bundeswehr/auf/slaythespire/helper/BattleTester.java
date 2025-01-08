package de.bundeswehr.auf.slaythespire.helper;

import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.map.act.Act;
import de.bundeswehr.auf.slaythespire.model.map.act.ActFour;
import de.bundeswehr.auf.slaythespire.model.map.act.ActOne;
import de.bundeswehr.auf.slaythespire.model.map.act.ActTwo;
import de.bundeswehr.auf.slaythespire.model.map.field.FieldEnum;
import de.bundeswehr.auf.slaythespire.model.player.TestPlayer;
import de.bundeswehr.auf.slaythespire.model.potion.CheaterPotion;
import de.bundeswehr.auf.slaythespire.model.potion.uncommon.DistilledChaosPotion;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;
import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Keil, Vladislav
 */
public class BattleTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player = TestPlayer.cheater(primaryStage);

        player.getPotions().add(new CheaterPotion());
        player.getPotions().add(new DistilledChaosPotion());

        GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);

        // Act I
        GuiHelper.Scenes.startBattleScene(player, actOneGenerateEnemies(player), FieldEnum.ENEMY_FIELD);
//        GuiHelper.Scenes.startBattleScene(player, actOneGenerateElitesEnemies(player), FieldEnum.ELITE_FIELD);
//        GuiHelper.Scenes.startBattleScene(player, actOneGenerateBossEnemies(player), FieldEnum.BOSS_FIELD);

        // Act II
//        player.setCurrentAct(2);
//        GuiHelper.Scenes.startBattleScene(player, actTwoGenerateEnemies(player), FieldEnum.ENEMY_FIELD);
//        GuiHelper.Scenes.startBattleScene(player, actTwoGenerateElitesEnemies(player), FieldEnum.ELITE_FIELD);
//        GuiHelper.Scenes.startBattleScene(player, actTwoGenerateBossEnemies(player), FieldEnum.BOSS_FIELD);

        // TODO Act III

        // Act IV
//        player.setCurrentAct(4);
//        GuiHelper.Scenes.startBattleScene(player, actFourGenerateElitesEnemies(player), FieldEnum.ELITE_FIELD);
//        GuiHelper.Scenes.startBattleScene(player, actFourGenerateBossEnemies(player), FieldEnum.BOSS_FIELD);
    }

    private List<Enemy> actOneGenerateBossEnemies(TestPlayer player) {
        return invoke(new ActOne(player), "createBossEnemies");
    }

    private List<Enemy> actOneGenerateElitesEnemies(TestPlayer player) {
        return invoke(new ActOne(player), "createElitesEnemies");
    }

    private List<Enemy> actOneGenerateEnemies(TestPlayer player) {
        return invoke(new ActOne(player), "createEnemies");
    }

    private List<Enemy> actTwoGenerateBossEnemies(TestPlayer player) {
        return invoke(new ActTwo(player), "createBossEnemies");
    }

    private List<Enemy> actTwoGenerateElitesEnemies(TestPlayer player) {
        return invoke(new ActTwo(player), "createElitesEnemies");
    }

    private List<Enemy> actTwoGenerateEnemies(TestPlayer player) {
        return invoke(new ActTwo(player), "createEnemies");
    }

    private List<Enemy> actFourGenerateBossEnemies(TestPlayer player) {
        return invoke(new ActFour(player), "createBossEnemies");
    }

    private List<Enemy> actFourGenerateElitesEnemies(TestPlayer player) {
        return invoke(new ActFour(player), "createElitesEnemies");
    }

    @SuppressWarnings("unchecked")
    private List<Enemy> invoke(Act act, String name) {
        try {
            Method method = act.getClass().getDeclaredMethod(name);
            method.setAccessible(true);
            return (List<Enemy>) method.invoke(act);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

}
