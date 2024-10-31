package controller.menus;

import models.DifficultyLevel;
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

    public void getBacktoMenu(){
        MainMenuViewController mainMenu = new MainMenuViewController();
        playerName = mainMenu.playerName;
        mainMenu.startMenu();
    }

    /**
     * selectChar ruft newPlayer() auf -> dort wird erst wirklich der Charakter ausgewählt.
     * Hier wird der Spieler mit dessen übergebenem playerName begrüßt und eine Option zum zurückkehren zum Main Menu
     * mittels eingabe von "exit" gestellt.
     * @param playerName
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
     * newPlayer() erstellt je nach Benutzereingabe einen neuen Charakter und übergibt diese Auswahl an startMapView().
     * Eine falsche Eingabe, oder die Eingabe "exit" führt zurück zu selectChar()
     */
    private void newPlayer(){
        String selectedCharacter;
        System.out.print("Type the number of corresponding character wich you'd like to choose: ");
        selectedCharacter = in.next();

        if (selectedCharacter.equals("1")) {
            Ironclad player = new Ironclad();
            System.out.println("\nYou chose: Ironclad");
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

    public DifficultyLevel setDifficulty(){
        String dif;
        System.out.println("Set difficulty level: ");
        dif = in.next();
        switch (dif){
            case "1":
                return DifficultyLevel.SUPEREASY;
            case "2":
                return DifficultyLevel.EASY;
            case "3":
                return DifficultyLevel.NORMAL;
            case "4":
                break;
            case "5":
                break;
            default:
                selectChar(playerName);
        }
        return null;
    }

    private void startMapView(Player player){
        DifficultyLevel difficulty = setDifficulty();
        System.out.println("\nStarting map view...");
    }
}
