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
import de.bundeswehr.auf.slaythespire.model.settings.structure.Normal;
import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Keil, Vladislav
 */
public class BattleTester extends Application {

    private enum Type {
        CHEATER, DEFENSIVE, CUSTOM, IRONCLAD, SILENT
    }

    private enum Test {
        ACT_I_ENEMY, ACT_I_ELITE, ACT_I_BOSS,
        ACT_II_ENEMY, ACT_II_ELITE, ACT_II_BOSS,
        ACT_III_ENEMY, ACT_III_ELITE, ACT_III_BOSS,
        ACT_IV_ELITE, ACT_IV_BOSS
    }

    private static final Test TEST = Test.ACT_I_ENEMY;
    private static final Type TYPE = Type.CHEATER;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestPlayer player;
        switch (TYPE) {
            case IRONCLAD:
                player = TestPlayer.ironclad(primaryStage);
                break;
            case SILENT:
                player = TestPlayer.silent(primaryStage);
                break;
            case DEFENSIVE:
                player = TestPlayer.defensive(primaryStage);
                break;
            case CUSTOM:
                player = TestPlayer.custom(primaryStage);
                break;
            default:
                player = TestPlayer.cheater(primaryStage);
        }

        player.getPotions().add(new CheaterPotion());
        player.getPotions().add(new DistilledChaosPotion());

        GameSettings.setDifficultyLevel(new Normal());

        switch (TEST) {
            case ACT_II_ENEMY:
            case ACT_II_ELITE:
            case ACT_II_BOSS:
                player.setCurrentAct(2);
                break;
            case ACT_IV_ELITE:
            case ACT_IV_BOSS:
                player.setCurrentAct(4);
                break;
        }
        switch (TEST) {
            // Act I
            case ACT_I_ENEMY:
                GuiHelper.Scenes.startBattleScene(player, actOneGenerateEnemies(player), FieldEnum.ENEMY_FIELD);
                break;
            case ACT_I_ELITE:
                GuiHelper.Scenes.startBattleScene(player, actOneGenerateElitesEnemies(player), FieldEnum.ELITE_FIELD);
                break;
            case ACT_I_BOSS:
                GuiHelper.Scenes.startBattleScene(player, actOneGenerateBossEnemies(player), FieldEnum.BOSS_FIELD);
                break;
            // Act II
            case ACT_II_ENEMY:
                player.setCurrentAct(2);
                GuiHelper.Scenes.startBattleScene(player, actTwoGenerateEnemies(player), FieldEnum.ENEMY_FIELD);
                break;
            case ACT_II_ELITE:
                GuiHelper.Scenes.startBattleScene(player, actTwoGenerateElitesEnemies(player), FieldEnum.ELITE_FIELD);
                break;
            case ACT_II_BOSS:
                GuiHelper.Scenes.startBattleScene(player, actTwoGenerateBossEnemies(player), FieldEnum.BOSS_FIELD);
                break;
            // TODO Act III
            // Act IV
            case ACT_IV_ELITE:
                GuiHelper.Scenes.startBattleScene(player, actFourGenerateElitesEnemies(player), FieldEnum.ELITE_FIELD);
                break;
            case ACT_IV_BOSS:
                GuiHelper.Scenes.startBattleScene(player, actFourGenerateBossEnemies(player), FieldEnum.BOSS_FIELD);
                break;
        }
    }

    private List<Enemy> actFourGenerateBossEnemies(TestPlayer player) {
        return invoke(new ActFour(player), "createBossEnemies");
    }

    private List<Enemy> actFourGenerateElitesEnemies(TestPlayer player) {
        return invoke(new ActFour(player), "createElitesEnemies");
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
