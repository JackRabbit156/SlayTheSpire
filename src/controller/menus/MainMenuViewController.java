package controller.menus;

import view.CreditView;
import view.menus.MainMenuView;

import java.io.IOException;
import java.util.Scanner;

public class MainMenuViewController {
    private MainMenuView mainMenu;
    private CharacterMenuViewController playerCharacter;
    private CreditView credits;
    public String playerName;

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
        mainMenu.displayMenu();
        input = in.next();

        switch(input){
            case "1":
                // Hier wird bereits der username abgefragt und an die characterselection übergeben
                System.out.println("Choose a username. Typing \"exit\" will always get you back to main menu!");
                playerName = in.next();
                playerCharacter.selectChar(playerName);
                break;
            case "2":
                //loadSaveGame();
                //TODO: Spiel Laden hinzufügen.

            case "3":
                // Delete Savegame();
                // TODO: Delete Savegame Methode für das Hauptmenü implementieren.
                System.out.println("Coming soon");
                startMenu();
                break;
            case "4":
                credits.showCredits();
                startMenu();
                break;
            case "5":
                System.out.println("You chose \"5. Quit\" Game will close. ");
                return;
            default:
                System.out.println("Wrong input. Please choose from the following options.\n");
                startMenu();
                break;
        }
    }

}
