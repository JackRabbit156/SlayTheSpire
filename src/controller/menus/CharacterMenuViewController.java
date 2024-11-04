package controller.menus;

import controller.MapViewController;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.game_settings.structure.GameMode;
import models.player.Ironclad;
import models.player.player_structure.Player;
import view.menus.CharacterMenuView;
import view.menus.MainMenuView;

import java.util.Scanner;

public class CharacterMenuViewController {
    private String selectedCharacter;
    private CharacterMenuView charView;
    private Scanner in = new Scanner(System.in);

    public CharacterMenuViewController(){
        charView = new CharacterMenuView();
    }

    //Führt zurück zum Main Menu
    public void getBacktoMenu(){
        MainMenuViewController mainMenu = new MainMenuViewController();
        mainMenu.startMenu();
    }
    //Führt zurück zur Charakterauswahl
    public void getBacktoCharSelection(){
        System.out.println("Choose a username: ");
        MainMenuViewController.playerName = in.next();
        selectChar(MainMenuViewController.playerName);
    }

    /**
     * ruft newPlayer() auf -> dort wird erst der Charakter ausgewählt.
     * Hier wird der Spieler mit dessen übergebenem playerName begrüßt und eine Option zum zurückkehren zum Main Menu
     * mittels eingabe von "exit" gestellt.
     * @param playerName username
     */
    public void selectChar(String playerName){
        System.out.println("\nHello! " + playerName + "\n");
        charView.charMenu();

        if (playerName.toLowerCase().equals("exit")) {
            getBacktoMenu();
        }
        newPlayer();
    }

    /**
     * erstellt je nach Benutzereingabe einen neuen Charakter und übergibt diese Auswahl an startMapView().
     * Eine falsche Eingabe, oder die Eingabe "exit" führt zurück zu selectChar()
     */
    private void newPlayer(){
        System.out.print("Type the number of corresponding character which you'd like to choose: ");
        selectedCharacter = in.next();

        if (selectedCharacter.equals("1")) {
            Ironclad player = new Ironclad();
            System.out.println("\nAwesome! you chose: Ironclad\n");
            selectedCharacter = "Ironclad";
            startMapView(player);
        }
        else if(selectedCharacter.equals("2")) {
            Ironclad player = new Ironclad();
            System.out.println("\nYou chose: Silent, but Silent isn't available, Game will start with Ironclad");
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
     * @return DifficultyLevel
     */
    private void chooseDifficulty(){
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
                System.out.println("\tWrong input... going back to character selection\t\n\n");
                getBacktoCharSelection();
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
                System.out.println("You chose: 1. Normal Mode");
                GameSettings.setGameMode(GameMode.NORMAL);
                break;
            case "2":
                System.out.println("You chose: 2. Hardcore Mode");
                GameSettings.setGameMode(GameMode.HARDCORE);
                break;
            default:
                getBacktoMenu();
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
            System.out.println("\nStarting map view...");
            MapViewController map = new MapViewController(player);
        }
        else if (start.toLowerCase().equals("n")) {
            System.out.println();
            System.out.println("\nGoing back to character selection");
            getBacktoCharSelection();
        }
        else {
            System.out.println("\tWrong input... going back to character selection\t\n\n");
            getBacktoCharSelection();
        }
    }
}
