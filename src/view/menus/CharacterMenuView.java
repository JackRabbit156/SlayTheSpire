package view.menus;

public class CharacterMenuView {
    /**
     * Darstellung der Charaktere die zur Auswahl stehen
     * @author Loeschner, Marijan
     */
    public void charMenu(){

        StringBuilder sb = new StringBuilder();
        System.out.println("\n\tPlease choose a Character: ");
        sb.append("(1)Ironclad: " + "\t\t\t(2)Silent: (Coming Soon)\n");
        sb.append("  Deck: 5 Strike" + "\t Deck: 5 Strike\n");
        sb.append("\t\t4 Defend" + "\t\t   5 Defend\n");
        sb.append("\t\t1 Bash" + "\t\t\t   1 Survivor\n");
        sb.append("               \t\t\t   1 Neutralize\n");
        System.out.println(sb);
    }
}
