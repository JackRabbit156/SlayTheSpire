package controller.menus;

import controller.MapViewController;
import view.CreditView;
import view.menus.MainMenuView;

import java.io.IOException;
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
        Scanner in = new Scanner(System.in);
        String input;
        String quit;
        mainMenu.displayMenu();
        input = in.next();

        switch(input){
            case "1":
                // Hier wird bereits der username abgefragt und an die characterselection übergeben
                System.out.print("\nChoose a username: ");
                playerName = in.next();
                System.out.println("\nTyping \"exit\" will always get you back to main menu!");
                playerCharacter.selectChar(playerName);
                break;
            case "2":
                //loadSaveGame();
                //TODO: Spiel Laden hinzufügen.

            case "3":
                // deleteSavegame();
                // TODO: Delete Savegame Methode für das Hauptmenü implementieren.
                System.out.println("Coming soon");
                startMenu();
                break;
            case "4":
                credits.showCredits();
                startMenu();
                break;
            case "5":
                System.out.println("Are you sure, that you want to quit the Game? (Y/N). ");
                quit = in.next();
                if (quit.toLowerCase().equals("y")) {
                    System.out.println("\nGood bye, see you soon");
                    System.exit(0);
                }
                else if (quit.toLowerCase().equals("n")) {
                    startMenu();
                }
                else {
                    System.out.println("\tWrong input... going back to Main Menu\t\n\n");
                    startMenu();
                }
                break;
            default:
                System.out.println("Wrong input. Please choose from the following options.\n");
                startMenu();
                break;
        }
    }

}
