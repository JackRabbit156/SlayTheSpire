package controller.menus;

import controller.MapViewController;
import models.game_settings.GameSettings;
import models.game_settings.structure.DifficultyLevel;
import models.game_settings.structure.GameMode;
import models.player.Ironclad;
import models.player.player_structure.Player;
import view.menus.CharacterMenuView;

import java.util.Scanner;

public class CharacterMenuViewController {
    public String playerName;
    private CharacterMenuView charView;
    private Scanner in = new Scanner(System.in);

    public CharacterMenuViewController(){
        charView = new CharacterMenuView();
    }

    //Führt zurück zum Main Menu
    public void getBacktoMenu(){
        MainMenuViewController mainMenu = new MainMenuViewController();
        playerName = mainMenu.playerName;
        mainMenu.startMenu();
    }

    /**
     * ruft newPlayer() auf -> dort wird erst der Charakter ausgewählt.
     * Hier wird der Spieler mit dessen übergebenem playerName begrüßt und eine Option zum zurückkehren zum Main Menu
     * mittels eingabe von "exit" gestellt.
     * @param playerName username
     */
    public void selectChar(String playerName){
        System.out.println("Hello! " + playerName + "\n");
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
        String selectedCharacter;
        System.out.print("Type the number of corresponding character which you'd like to choose: ");
        selectedCharacter = in.next();

        if (selectedCharacter.equals("1")) {
            Ironclad player = new Ironclad();
            System.out.println("\nAwesome! you chose: Ironclad\n");
            startMapView(player);
        }
        else if(selectedCharacter.equals("2")) {
            Ironclad player = new Ironclad();
            System.out.println("\nYou chose: Silent, but Silent isn't available, Game will start with Ironclad");
            startMapView(player);
        }
        else if (selectedCharacter.toLowerCase().equals("exit")){
            getBacktoMenu();
        }
        // Bei falscher Eingabe gehts zurück zum Anfang der character creation.
        else {
            System.out.println("\tWrong input... \t\n\n");
            System.out.println("Choose a username: ");
            playerName = in.next();
            selectChar(playerName);
        }
    }

    /**
     * setzt den entsprechenden Schwierigkeitsgrad
     * @return DifficultyLevel
     */
    private DifficultyLevel setDifficulty(){
        String dif;
        charView.difDisplay();
        dif = in.next();
        switch (dif){
            case "1":
                return DifficultyLevel.SUPEREASY;
            case "2":
                return DifficultyLevel.EASY;
            case "3":
                return DifficultyLevel.NORMAL;
            case "4":
                //Platzhalter für DifficultyLevel.HARD
                //break;
            case "5":
                //Platzhalter für DifficultyLevel.IMPOSSIBLE
                //break;
            default:
                System.out.println("\tWrong input... \t\n\n");
                System.out.println("Choose a username: ");
                playerName = in.next();
                selectChar(playerName);
                break;
        }
        return null;
    }

    private GameMode setGameMode(){
        String mode;
        charView.gameModeInfo();
        mode = in.next();
        switch(mode){
            case "1":
                System.out.println("You chose: 1. Normal Mode");
                return GameMode.NORMAL;
            case "2":
                System.out.println("You chose: 2. Hardcore Mode");
                return GameMode.HARDCORE;
            default:
                getBacktoMenu();
        }
        return null;
    }

    private void startMapView(Player player){
        GameSettings.difficultylevel = setDifficulty();
        GameSettings.gamemode = setGameMode();
        System.out.println("\nStarting map view...");
        MapViewController map = new MapViewController(player);
    }
}
