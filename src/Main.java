import controller.menus.MainMenuViewController;

public class Main {
    public static void main(String[] args) {
//        MapViewController map = new MapViewController(player);
        MainMenuViewController menu = new MainMenuViewController();
        menu.startMenu();

//        System.out.println(Color.values().length);
        /*Ironclad player = new Ironclad();

        List<Enemy> enemies = new ArrayList<Enemy>();

        enemies.add(new AcidSlime());
        enemies.add(new Cultist());
        enemies.add(new SpikeSlime());

        BattleViewController controller = new BattleViewController(player, enemies);
        controller.startBattle();*/
    }
}