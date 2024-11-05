package controller.menus;

import helper.Color;
import helper.ConsoleAssistent;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.game_settings.structure.GameMode;
import models.load_save_game_elements.GameSaveManager;
import models.player.player_structure.Player;
import view.menus.CharacterMenuView;
import view.menus.GameMenuView;

import java.util.Scanner;

/**
 * @author Keil, Vladislav
 */
public class GameMenuViewController {
    private LoadMenuViewController loadMenuViewController;
    private GameSaveManager gameSaveManager;
    private MainMenuViewController mainMenuViewController;

    private GameMenuView gameMenuView = new GameMenuView();
    private Scanner in = new Scanner(System.in);

    public GameMenuViewController (){
        gameSaveManager = new GameSaveManager();
    }

    public void display(Player player) {
        gameMenuView.displayMenu();
        System.out.print(">> ");
        String input = in.next();
        switch(input){
            case "1":
                if (GameSettings.getGameMode().equals(GameMode.HARDCORE)) {
                    ConsoleAssistent.println(Color.YELLOW, "In Hardcore-mode not available!");
                } else {
                    gameSaveManager.saveGame(player);
                }
                display(player);
                break;
            case "2":
                loadSaveGame();
                break;
            case "3":
                showChangeDifficulty();
                display(player);
                break;
            case "4":
                System.out.println("Are you sure, you want to return to Main Menu? (Y/N). ");
                String menu;
                menu = in.next();
                if (menu.toLowerCase().equals("y")) {
                    System.out.println("\nGoing back to Main Menu..");
                    ConsoleAssistent.sleep(100);
                    mainMenuViewController = new MainMenuViewController();
                    mainMenuViewController.startMenu();
                }
                else if (menu.toLowerCase().equals("n")) {
                    display(player);
                }
                break;
            case "5":
                System.out.println("Are you sure, you want to quit the Game? (Y/N). ");
                String quit;
                quit = in.next();
                if (quit.toLowerCase().equals("y")) {
                    System.out.println("\nGood bye, see you soon");
                    System.exit(0);
                }
                else if (quit.toLowerCase().equals("n")) {
                    display(player);
                }
                else {
                    System.out.println("\tWrong input... going back to Game Menu\t\n\n");
                    display(player);
                }
                break;
            case "0":
                return;
            default:
                System.out.println("\tWrong input... going back to character selection\t\n\n");
                display(player);
                break;
        }
    }

    private void loadSaveGame() {
        loadMenuViewController = new LoadMenuViewController();
        loadMenuViewController.showLoadMenu();
    }

    private void showChangeDifficulty() {
        CharacterMenuView charView = new CharacterMenuView();
        charView.difDisplay();
        String dif = in.next();
        switch (dif){
            case "1":
                GameSettings.setDifficultyLevel(DifficultyLevel.SUPEREASY);
                break;
            case "2":
                GameSettings.setDifficultyLevel(DifficultyLevel.EASY);
                break;
            case "3":
                GameSettings.setDifficultyLevel(DifficultyLevel.NORMAL);
                break;
            case "4":
                //Platzhalter für DifficultyLevel.HARD
                //break;
            case "5":
                //Platzhalter für DifficultyLevel.IMPOSSIBLE
                //break;
            default:
                if (dif.toLowerCase().equals("exit")) {
                    return;
                }
                else {
                    System.out.println("\tWrong input... going back to character selection\t\n\n");
                }
                break;
        }
    }
}
