package view;

import controller.menus.MainMenuViewController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CreditView {
    private MainMenuViewController mainMenu;
    public CreditView(){
    }

    public void showCredits() {
        Scanner out = new Scanner(System.in);
        String credits = "   _____              _ _ _       \n" +
                "  / ____|            | (_) |      \n" +
                " | |     _ __ ___  __| |_| |_ ___ \n" +
                " | |    | '__/ _ \\/ _` | | __/ __|\n" +
                " | |____| | |  __/ (_| | | |_\\__ \\\n" +
                "  \\_____|_|  \\___|\\__,_|_|\\__|___/\n" +
                "                                  ";
        String D = "Daniel Willig";
        String V = "Vladislav Keil";
        String A = "Alexander Warawa";
        String M = "Marijan Löschner";

        System.out.println(credits);
        System.out.println("Programmers: \n\t" + D + "\n\t" + V + "\n\t" + A + "\n\t" + M);
        //TODO: vor Abschluss evtl nochmal überarbeiten :)
        System.out.println("\nType anything and press Return Key to get back to Main Menu");
        out.next();
    }
}
