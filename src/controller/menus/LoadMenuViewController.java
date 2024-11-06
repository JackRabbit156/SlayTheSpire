package controller.menus;

import controller.MapViewController;
import helper.ConsoleAssistent;
import models.cards.DeckFactory;
import models.cards.card_structure.Card;
import models.game_settings.GameSettings;
import models.load_save_game_elements.GameSaveManager;
import models.load_save_game_elements.SaveFilePreview;
import models.player.Ironclad;
import models.player.Silent;
import models.player.player_structure.Player;
import view.menus.LoadMenuView;

import java.util.*;

/**
 * Diese Klasse verwaltet den Ladevorgang von gespeicherten Spielen im Menü.
 * Sie bietet Funktionen zum Anzeigen von Speicherdateien, Laden von Spielen
 * und Löschen von Speicherdateien, sowie auch das Speichern selbst.
 *
 * @author Warawa Alexander
 */

public class LoadMenuViewController {

    private LoadMenuView loadMenuView;
    private GameSaveManager gameSaveManager;

    public LoadMenuViewController (){
        loadMenuView = new LoadMenuView();
        gameSaveManager = new GameSaveManager();
    }

    /**
     * Speichert das aktuelle Spiel des angegebenen Spielers.
     *
     * @param player Der Spieler, dessen Spiel gespeichert werden soll.
     */
    public void saveGame(Player player){
        deleteSaveFileWithName(GameSettings.lastSession);
        gameSaveManager.saveGame(player);
    }

    /**
     * Zeigt das Lademenü an, in dem der Spieler eine Speicherdatei auswählen kann,
     * um ein vorheriges Spiel zu laden.
     */
    public void showLoadMenu(){
        List<SaveFilePreview> saveFilePreviewList = saveFilePreviewList();
        if(saveFilePreviewList.isEmpty()){
            System.out.println(asciiArtStringEmpty());
            System.out.println("\nNo Save-Files!");
            ConsoleAssistent.sleep(1000);
            return;
        }
        loadMenuView.displaySaveStates(saveFilePreviewList());

        int selectedSaveFile = 0;
        while(true){
            System.out.print("\nChoose the Game state you want to load: ");
            try{
                selectedSaveFile = Integer.parseInt(new Scanner(System.in).nextLine());
                if(selectedSaveFile > saveFilePreviewList.size()){
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input...");
            }
        }

        startLoadedGame(selectedSaveFile - 1);
    }

    /**
     * Zeigt das Menü zum Löschen von Speicherdateien an,
     * in dem der Spieler eine Speicherdatei auswählen kann, um sie zu löschen.
     */
    public void showDeleteMenu(){
        List<SaveFilePreview> saveFilePreviewList = saveFilePreviewList();
        if(saveFilePreviewList.isEmpty()){
            System.out.println(asciiArtStringEmpty());
            System.out.println("\nNo Save-Files!");
            ConsoleAssistent.sleep(1000);
            return;
        }
        loadMenuView.displaySaveStates(saveFilePreviewList());

        int selectedSaveFile = 0;
        while(true){
            System.out.print("\nChoose the Game state you want to delete: ");
            try{
                selectedSaveFile = Integer.parseInt(new Scanner(System.in).nextLine());
                if(selectedSaveFile > saveFilePreviewList.size()){
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input...");
            }
        }

        deleteSaveFileWithId(selectedSaveFile-1);
        ConsoleAssistent.sleep(1000);
    }

    /**
     * Löscht die Speicherdatei mit dem angegebenen Namen.
     *
     * @param nameOfFile Der Name der zu löschenden Speicherdatei.
     */
    private void deleteSaveFileWithName(String nameOfFile){
        gameSaveManager.deleteSelectedSaveFile(nameOfFile);
    }

    /**
     * Löscht die Speicherdatei mit der angegebenen ID.
     *
     * @param id Die ID der zu löschenden Speicherdatei.
     */
    private void deleteSaveFileWithId(int id){
        gameSaveManager.deleteSelectedSaveFile(id);
    }

    /**
     * Startet das Spiel mit den Daten aus der angegebenen Speicherdatei.
     *
     * @param id Die ID der Speicherdatei, die geladen werden soll.
     */
    private void startLoadedGame(int id){
        Map<String, String> gameData = gameSaveManager.loadGame(id);
        Player player = null;

        String playerTypeAsString = gameData.get("character");
        switch (playerTypeAsString){
            case "IRONCLAD": player = new Ironclad(); break;
            case "SILENT": player = new Silent(); break;
            default:
                System.out.println("Weird...");return;
        }

        player.setUsername(gameData.get("username"));
        player.setCurrentHealth(Integer.parseInt(gameData.get("currentHealth")));

        player.setCurrentAct(Integer.parseInt(gameData.get("currentAct")));

        player.setCurrentField(gameData.get("field"));
        player.setGold( Integer.parseInt(gameData.get("gold")));

        List<Card> deck = new ArrayList<>();
        for(int i = 0; gameData.get("card"+i) != null; i++){
            String cardName = gameData.get("card"+i);

            Card card = DeckFactory.assignCard(cardName);
            deck.add(card);
        }

        player.setDeck(deck);

        GameSettings.setTimerSeconds(Integer.parseInt(gameData.get("seconds")));
        GameSettings.setTimerMinutes(Integer.parseInt(gameData.get("minutes")));
        GameSettings.setTimerHours(Integer.parseInt(gameData.get("hours")));

        GameSettings.lastSession = gameData.get("lastSession");


        MapViewController map = new MapViewController(player, true);
    }

    /**
     * Gibt eine Liste der Vorschau-Daten für die Speicherdateien zurück.
     *
     * @return Eine Liste von SaveFilePreview-Objekten.
     */
    public List<SaveFilePreview> saveFilePreviewList(){
        return gameSaveManager.listSaveFiles();
    }

    /**
     * Gibt eine ASCII-Art-Nachricht zurück, die angezeigt wird,
     * wenn keine Speicherdateien vorhanden sind.
     *
     * @return Ein String, der die ASCII-Art darstellt.
     */
    private String asciiArtStringEmpty(){
        String empty = " ___ __ __ ___ _______   __\n" +
                "| __|  V  | _,\\_   _\\ `v' /\n" +
                "| _|| \\_/ | v_/ | |  `. .' \n" +
                "|___|_| |_|_|   |_|   !_!";
        return empty;
    }
}