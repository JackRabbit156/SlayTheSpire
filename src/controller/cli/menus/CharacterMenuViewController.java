package controller.cli.menus;

import controller.cli.MapViewController;
import helper.ConsoleAssistent;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.game_settings.structure.GameMode;
import models.player.Ironclad;
import models.player.player_structure.Player;
import view.cli.menus.CharacterMenuView;

import java.util.Scanner;

/**
 * Klasse zum erstellen eines Charakters wenn ein neues Spiel gestartet wird
 * @author Loeschner, Marijan
 * @author Warawa, Alexander
 */
public class CharacterMenuViewController {
    private String selectedCharacter;
    private CharacterMenuView charView;
    private Scanner in = new Scanner(System.in);

    public CharacterMenuViewController(){
        charView = new CharacterMenuView();
    }

    //Führt zurück zum app.Main Menu
    public void getBacktoMenu(){
        MainMenuViewController mainMenu = new MainMenuViewController();
        mainMenu.startMenu();
    }
    //Führt zurück zur Charakterauswahl
    public void getBacktoCharSelection(){
        System.out.print("\n\tChoose a username: ");
        MainMenuViewController.playerName = in.next();
        selectChar(MainMenuViewController.playerName);
    }

    /**
     * Ruft newPlayer() auf, heißt dort wird erst der Charakter ausgewählt.
     * Hier wird der Spieler mit dessen übergebenem playerName begrüßt und eine Option zum zurückkehren zum app.Main Menu
     * mittels eingabe von "exit" gestellt.
     * @param playerName username
     */
    public void selectChar(String playerName){
        System.out.println("\n\tHello! " + playerName + "\n");
        charView.charMenu();

        if (playerName.toLowerCase().equals("exit")) {
            getBacktoMenu();
        }
        newPlayer(playerName);
    }

    /**
     * erstellt je nach Benutzereingabe einen neuen Charakter und übergibt diese Auswahl an startMapView().
     * Eine falsche Eingabe, oder die Eingabe "exit" führt zurück zu selectChar()
     */
    private void newPlayer(String username){
        selectedCharacter = in.next();

        if (selectedCharacter.equals("1")) {
            Ironclad player = new Ironclad();
            player.setUsername(username);
            selectedCharacter = "Ironclad";
            startMapView(player);
        }
        else if(selectedCharacter.equals("2")) {
            Ironclad player = new Ironclad();
            System.out.println("\n\tYou chose: Silent, but Silent is not yet available, your game will start with Ironclad");
            selectedCharacter = "Ironclad";
            startMapView(player);
        }
        else if (selectedCharacter.toLowerCase().equals("exit")){
            getBacktoMenu();
        }
        // Bei falscher Eingabe gehts zurück zum Anfang der character creation.
        else {
            System.out.println("\tWrong input... going back to character selection\t\n\n");
            getBacktoCharSelection();
        }
    }

    /**
     * Setzt den entsprechenden Schwierigkeitsgrad
     */
    public void chooseDifficulty(){
        String dif;
        charView.difDisplay();
        dif = in.next();
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
                    getBacktoMenu();
                }
                else {
                    System.out.println("\tWrong input... going back to character selection\t\n\n");
                    getBacktoCharSelection();
                }
                break;
        }
    }

    /**
     * Setzt den SpielModus, (1. Normal Mode, 2. Hardcore Mode)
     * mit Erklärung zum entsprechenden Spielmodus
     * @return GameMode
     */
    private void chooseGameMode(){
        String mode;
        charView.gameModeInfo();
        mode = in.next();
        switch(mode){
            case "1":
                GameSettings.setGameMode(GameMode.NORMAL);
                break;
            case "2":
                GameSettings.setGameMode(GameMode.HARDCORE);
                break;
            default:
                if (mode.toLowerCase().equals("exit")) {
                getBacktoMenu();
                }
                else {
                    System.out.println("\tWrong input... going back to character selection\t\n\n");
                    getBacktoCharSelection();
                }
                break;
        }
    }


    private void startMapView(Player player){
        String start;
        chooseDifficulty();
        chooseGameMode();
        charView.characterOverview(selectedCharacter);
        start = in.next();
        if (start.toLowerCase().equals("y")) {
            System.out.println("\nStarting a new game...");
            MapViewController map = new MapViewController(player, false);
        }
        else if (start.toLowerCase().equals("n")) {
            System.out.println("\nGoing back to character selection");
            getBacktoCharSelection();
        }
        else if (start.toLowerCase().equals("exit")) {
            getBacktoMenu();
        }
        else {
            System.out.println("\tWrong input... going back to character selection\t\n\n");
            ConsoleAssistent.clearScreen();
            getBacktoCharSelection();
        }
    }
}
