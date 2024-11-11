package view.cli.menus;

import controller.cli.menus.MainMenuViewController;
import models.game_settings.GameSettings;

public class CharacterMenuView {
    /**
     * Darstellung der Charaktere die zur Auswahl stehen
     * @author Loeschner, Marijan
     */
    public void charMenu(){

        StringBuilder sb = new StringBuilder();

        sb.append("\t(1)Ironclad: " + "\t\t\t(2)Silent: (Coming Soon)\n");
        sb.append("\t  Deck: 5 Strike" + "\t Deck: 5 Strike\n");
        sb.append("\t\t\t4 Defend" + "\t\t   5 Defend\n");
        sb.append("\t\t\t1 Bash" + "\t\t\t   1 Survivor\n");
        sb.append("               \t\t\t\t   1 Neutralize\n");
        System.out.println(sb);
        System.out.print("\tPlease choose a Character: ");
    }

    public void difDisplay(){
        System.out.print(" \n\t 1. Super-Easy \n\t 2. Easy \n\t 3. Normal \n\n\tSet difficulty level: ");
    }

    public void gameModeInfo(){
        StringBuilder str = new StringBuilder();
        str.append("\n\n\t\tExplanation:");
        str.append("\n\t\t1.Normal Mode: \t\t\t\t\t 2.Hardcore Mode: ");
        str.append("\n\t\tOn Normal Mode you can Play  \t On hardcore Mode you can not");
        str.append("\n\t\tand save the game as you like.   save the game. once you die,");
        str.append("\n\t\t\t\t\t\t\t\t\t\t the Game is Over!\n");
        System.out.print(str + "\n\tChoose a Gamemode: ");
    }


    public void characterOverview(String selectedCharacter){
        String difficulty = GameSettings.getDifficultyLevel().toString();
        String gamemode = GameSettings.getGameMode().toString();
        String username = MainMenuViewController.playerName;
        System.out.print("\n\n\tYour Selection: \n\t\tPlayer: " + username +
                "\n\t\tCharacter: " + selectedCharacter + "\n\t\tDifficulty: " + difficulty + "\n\t\tGame Mode: " + gamemode +
                "\n\n\tDo you want to start the game with your selection? (Y/N) ");
    }

}
