package controller.menus;

import controller.MapViewController;
import models.player.Ironclad;
import models.player.Silent;
import models.player.player_structure.Player;
import view.menus.CharacterMenuView;

import java.util.Scanner;

public class CharacterMenuViewController {
    private CharacterMenuView charView;

    public CharacterMenuViewController(){
        charView = new CharacterMenuView();
    }

    /**
     * selectChar ruft newPlayer() auf -> dort wird erst wirklich der Charakter ausgewählt.
     * Hier wird der Spieler mit dessen übergebenem playerName begrüßt und eine Option zum zurückkehren zum Main Menu
     * mittels eingabe von "exit" gestellt.
     * @param playerName
     */
    public void selectChar(String playerName){
        MainMenuViewController mainMenu = new MainMenuViewController();
        System.out.println("Hello! " + playerName + "\n");
        charView.charMenu();

        if (playerName.toLowerCase().equals("exit")) {
            mainMenu.startMenu();
            return;
        }
        newPlayer();
    }

    /**
     * newPlayer() erstellt je nach Benutzereingabe einen neuen Charakter und übergibt diese Auswahl an startMapView().
     * Eine falsche Eingabe, oder die Eingabe "exit" führt zurück zu selectChar()
     */
    private void newPlayer(){
        MainMenuViewController mainMenu = new MainMenuViewController();
        Scanner in = new Scanner(System.in);
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
            mainMenu.startMenu();
        }
        // Bei falscher Eingabe gehts zurück zum Anfang der character creation.
        else {
            System.out.println("\tWrong input... \t\n\n");
            System.out.println("Choose a username: ");
            mainMenu.playerName = in.next();
            selectChar(mainMenu.playerName);
        }
    }
    private void startMapView(Player player){
        System.out.println("\nStarting map view...");
        MapViewController map = new MapViewController(player, false);
    }
}
