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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LoadMenuViewController {

    private LoadMenuView loadMenuView;
    private GameSaveManager gameSaveManager;

    public LoadMenuViewController (){
        loadMenuView = new LoadMenuView();
        gameSaveManager = new GameSaveManager();
    }

    public void saveGame(Player player){
        delteSaveFileWithName(GameSettings.lastSession);
        gameSaveManager.saveGame(player);
    }

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
                selectedSaveFile = new Scanner(System.in).nextInt() - 1;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input...");
            }
        }

        startLoadedGame(selectedSaveFile);

    }

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
                selectedSaveFile = new Scanner(System.in).nextInt() - 1;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input...");
            }
        }

        deleteSaveFileWithId(selectedSaveFile);
        ConsoleAssistent.sleep(1000);
    }


    private void delteSaveFileWithName(String nameOfFile){
        gameSaveManager.deleteSelcetedSaveFile(nameOfFile);
    }

    private void deleteSaveFileWithId(int id){
        gameSaveManager.deleteSelcetedSaveFile(id);
    }

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

        GameSettings.time.setSeconds(Integer.parseInt(gameData.get("seconds")));
        GameSettings.time.setMinutes(Integer.parseInt(gameData.get("minutes")));
        GameSettings.time.setHours(Integer.parseInt(gameData.get("hours")));

        GameSettings.lastSession = gameData.get("lastSession");

        MapViewController map = new MapViewController(player, true);
    }

    public List<SaveFilePreview> saveFilePreviewList(){
        return gameSaveManager.listSaveFiles();
    }

    private String asciiArtStringEmpty(){
        String empty = " ___ __ __ ___ _______   __\n" +
                "| __|  V  | _,\\_   _\\ `v' /\n" +
                "| _|| \\_/ | v_/ | |  `. .' \n" +
                "|___|_| |_|_|   |_|   !_!";
        return empty;
    }
}