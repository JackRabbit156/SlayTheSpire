package controller.cli.menus;

import helper.ConsoleAssistent;
import view.cli.CreditView;
import view.cli.menus.MainMenuView;

import java.util.Scanner;

public class MainMenuViewController {
    private MainMenuView mainMenu;
    private CharacterMenuViewController playerCharacter;
    private CreditView credits;
    public static String playerName;

    public MainMenuViewController(){
        mainMenu = new MainMenuView();
        playerCharacter = new CharacterMenuViewController();
        credits = new CreditView();
    }

    /**
     * Auswahl mit switch-case welcher der Menüpunkte ausgewählt werden möchte
     * ein falscher input ruft die Methode erneut auf.
     */
    public void startMenu() {
        ConsoleAssistent.clearScreen();

        Scanner in = new Scanner(System.in);
        String input;
        String quit;
        mainMenu.displayMenu();
        input = in.next();

        switch(input){
            case "1":
                // Hier wird bereits der username abgefragt und an die characterselection übergeben
                System.out.print("\n\tChoose a username: ");
                playerName = in.next();
                System.out.println("\n\tTyping \"exit\" will always get you back to main menu!");
                playerCharacter.selectChar(playerName);
                break;
            case "2":
                loadSaveGame();
                break;
            case "3":
                deleteSaveState();
                break;
            case "4":
                credits.showCredits();
                startMenu();
                break;
            case "5":
                System.out.println("\tAre you sure, that you want to quit the Game? (Y/N). ");
                quit = in.next();
                if (quit.toLowerCase().equals("y")) {
                    System.out.println("\n\tGood bye, see you soon");
                    System.exit(0);
                }
                else if (quit.toLowerCase().equals("n")) {
                    startMenu();
                }
                else {
                    System.out.println("\tWrong input... going back to app.Main Menu\t\n\n");
                    startMenu();
                }
                break;
            default:
                System.out.println("Wrong input. Please choose from the following options.\n");
                break;
        }

        startMenu();
    }

    private void loadSaveGame() {
        LoadMenuViewController loadMenu = new LoadMenuViewController();
        loadMenu.showLoadMenu();
    }

    private void deleteSaveState(){
        LoadMenuViewController loadMenu = new LoadMenuViewController();
        loadMenu.showDeleteMenu();
    }

}
