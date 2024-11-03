package tests;

import controller.LootViewController;
import models.player.Ironclad;
import models.player.player_structure.Player;

public class LootViewControllerTester {

    public static void main(String[] args) {
        Ironclad player = new Ironclad();
        String fieldType = "BossField";

        // TODO Schwierigkeit muss noch getestet / implementiert werden
        // Testfall 1 - Ironclad nach einem BossField
        System.out.println("Player hat " + player.getGold());
        LootViewController lootViewController = new LootViewController(player, fieldType);
        lootViewController.openLootView(player);
        System.out.println("Player hat 95 - 105G mehr.");

        //Testfall 2 - Ironcald nach einem EliteField
        fieldType = "EliteField";
        System.out.println("Player hat " + player.getGold());
        lootViewController = new LootViewController(player, fieldType);
        lootViewController.openLootView(player);
        System.out.println("Player hat 25 - 35G mehr.");

        // Testfall 3 - Ironclad nach einem EnemyField
        fieldType = "EnemyField";
        System.out.println("Player hat " + player.getGold());
        lootViewController = new LootViewController(player, fieldType);
        lootViewController.openLootView(player);
        System.out.println("Player hat 10 - 20G mehr.");
    }

}
