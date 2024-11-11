package view.cli.menus;

import helper.ConsoleAssistent;
import models.load_save_game_elements.SaveFilePreview;

import java.util.List;

public class LoadMenuView {

    public void displaySaveStates(List<SaveFilePreview> saveFilePreviewList){
        ConsoleAssistent.clearScreen();
        System.out.println(printTitle());

        for(int i = 0; i< saveFilePreviewList.size(); i++){
            System.out.printf("%nGame state: %d%n", i+1);
            System.out.printf("\t%-20s%s%n", "Last Session:", saveFilePreviewList.get(i).getLastSession());
            //System.out.printf("%n\t%-20s%s%n", "Nickname:", saveFilePreviewList.get(i).getCharacterName());
            System.out.printf("\t%-20s%s%n", "Chosen Character:", saveFilePreviewList.get(i).getPlayerType());
            System.out.printf("\t%-20s%s on Floor %s%n", "Act:", saveFilePreviewList.get(i).getCurrentAct(), saveFilePreviewList.get(i).getField());
            System.out.printf("\t%-20s%s%n", "Time Played:", saveFilePreviewList.get(i).getTimePlayed());
        }

        System.out.println();
    }

    private String printTitle(){
        String title = " _______  _______           _______    _______ _________ _______ _________ _______  _______ \n" +
                "(  ____ \\(  ___  )|\\     /|(  ____ \\  (  ____ \\\\__   __/(  ___  )\\__   __/(  ____ \\(  ____ \\\n" +
                "| (    \\/| (   ) || )   ( || (    \\/  | (    \\/   ) (   | (   ) |   ) (   | (    \\/| (    \\/\n" +
                "| (_____ | (___) || |   | || (__      | (_____    | |   | (___) |   | |   | (__    | (_____ \n" +
                "(_____  )|  ___  |( (   ) )|  __)     (_____  )   | |   |  ___  |   | |   |  __)   (_____  )\n" +
                "      ) || (   ) | \\ \\_/ / | (              ) |   | |   | (   ) |   | |   | (            ) |\n" +
                "/\\____) || )   ( |  \\   /  | (____/\\  /\\____) |   | |   | )   ( |   | |   | (____/\\/\\____) |\n" +
                "\\_______)|/     \\|   \\_/   (_______/  \\_______)   )_(   |/     \\|   )_(   (_______/\\_______)";

        return title;
    }
}
