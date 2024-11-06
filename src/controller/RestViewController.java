package controller;

import helper.Color;
import helper.ConsoleAssistent;
import models.player.player_structure.Player;
import view.RestSiteView;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Vladislav Keil
 */
public class RestViewController {
    private RestSiteView rest;
    private Player player;
    private Scanner scanner;


    public RestViewController(Player player) {
        this.player = player;
        this.rest = new RestSiteView();
        this.scanner = new Scanner(System.in);
    }

    public void startRest() {
        int input = 0;

        while(true){
            this.rest.display(player);
            this.rest.displayOptionChoiceMenu();
            try{
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                ConsoleAssistent.print(Color.YELLOW, "Wrong input...");
            }
        }

        switch (input) {
            case 2:
            case 3:
            case 1:
                int increasedHp = (int) (player.getMaxHealth() * 0.30);
                player.increaseCurrentHealth(increasedHp);
                break;
            case 0:
                break;
            default:
                ConsoleAssistent.print(Color.YELLOW, "Wrong input...");
                startRest();
                break;
        }
    }
}

