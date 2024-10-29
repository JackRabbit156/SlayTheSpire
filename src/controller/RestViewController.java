package controller;

import helper.ConsoleAssistent;
import models.player.player_structure.Player;
import view.RestSiteView;

import java.util.Scanner;

/**
 * @author Vladislav Keil
 */
public class RestViewController {
    private ConsoleAssistent assistent = new ConsoleAssistent();
    private RestSiteView rest = new RestSiteView();
    private Player player;
    private Scanner scanner;


    public RestViewController(Player player) {
        this.player = player;
        this.rest = new RestSiteView();
        this.scanner = new Scanner(System.in);
    }

    public void startRest() {
        assistent.clearScreen();
        rest.display(player);

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                int increasedHp = (int) (player.getMaxHealth() * 0.30);
                player.increaseCurrentHealth(increasedHp);
                break;
            case 2:
                break;
            default:
                System.out.println("Wrong input...");
                startRest();
                break;
        }
    }
}

