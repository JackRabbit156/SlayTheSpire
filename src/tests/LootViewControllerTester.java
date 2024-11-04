package tests;

import controller.LootViewController;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.player.Ironclad;
/**
 * @author Keil, Vladislav
 */
public class LootViewControllerTester {

    public static void main(String[] args) {
        Ironclad player = new Ironclad();
        String fieldType = "BossField";

        GameSettings.setDifficultyLevel(DifficultyLevel.EASY);
        // Testfall 1 - Ironclad nach einem BossField
        // Testfall 1 - EASY - 5 Karte zur Gold Faktor 1.5
        System.out.println("Player hat " + player.getGold());
        LootViewController lootViewController = new LootViewController(player, fieldType);
        lootViewController.openLootView(player);
        System.out.println("Player hat 95 - 105G mehr.");


        GameSettings.setDifficultyLevel(DifficultyLevel.HARD);
        //Testfall 2 - Ironcald nach einem EliteField
        //Testfall 2 - HARD - nur eine Karte zur Gold Faktor 0.5
        fieldType = "EliteField";
        System.out.println("Player hat " + player.getGold());
        lootViewController = new LootViewController(player, fieldType);
        lootViewController.openLootView(player);
        System.out.println("Player hat 25 - 35G mehr.");


        GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);
        // Testfall 3 - Ironclad nach einem EnemyField
        // Testfall 3 - Normal - nur 3 Karte zur Gold Faktor 1
        fieldType = "EnemyField";
        System.out.println("Player hat " + player.getGold());
        lootViewController = new LootViewController(player, fieldType);
        lootViewController.openLootView(player);
        System.out.println("Player hat 10 - 20G mehr.");
    }

}
