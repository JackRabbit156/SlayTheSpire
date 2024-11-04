package view.menus;

import controller.menus.CharacterMenuViewController;
import controller.menus.MainMenuViewController;
import models.game_settings.GameSettings;

public class CharacterMenuView {
    /**
     * Darstellung der Charaktere die zur Auswahl stehen
     * @author Loeschner, Marijan
     */
    public void charMenu(){

        StringBuilder sb = new StringBuilder();

        sb.append("(1)Ironclad: " + "\t\t\t(2)Silent: (Coming Soon)\n");
        sb.append("  Deck: 5 Strike" + "\t Deck: 5 Strike\n");
        sb.append("\t\t4 Defend" + "\t\t   5 Defend\n");
        sb.append("\t\t1 Bash" + "\t\t\t   1 Survivor\n");
        sb.append("               \t\t\t   1 Neutralize\n");
        System.out.println(sb);
        System.out.print("Please choose a Character: ");
    }

    public void difDisplay(){
        System.out.print(" \n 1. Super-Easy \n 2. Easy \n 3. Normal \n\nSet difficulty level: ");
    }

    public void gameModeInfo(){
        StringBuilder str = new StringBuilder();
        str.append("\n\n\t\tExplanation:");
        str.append("\n\t\t1.Normal Mode: \t\t\t\t\t 2.Hardcore Mode: ");
        str.append("\n\t\tOn Normal Mode you can Play  \t On hardcore Mode you can not");
        str.append("\n\t\tand save the game as you like.   save the game. once you die,");
        str.append("\n\t\t\t\t\t\t\t\t\t\t the Game is Over!\n");
        System.out.print(str + "\nChoose a Gamemode: ");
    }


    public void characterOverview(String selectedCharacter){
        String difficulty = GameSettings.getDifficultyLevel().toString();
        String gamemode = GameSettings.getGameMode().toString();
        String username = MainMenuViewController.playerName;
        System.out.print("\n\n\tPlayer: " + username +
                "\n\tCharacter: " + selectedCharacter + "\n\tDifficulty: " + difficulty + "\n\tGame Mode: " + gamemode +
                "\nDo you want to start the game with your selection? (Y/N)");
    }

}
