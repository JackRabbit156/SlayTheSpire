package models.event.act_one;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import models.event.Event;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * Der Spieler kann mit einer bestimmten Wahrscheinlichkeit Gold finden
 *
 * @author  Loeschner, Marijan
 */
public class DeadAdventurer extends Event {
    private static Image image = new Image("/images/event/act_one/DeadAdventurerEvent.png");
    private String title = "Big Fish";
    private String story = "\n\nYou come across a dead adventurer on the floor.\n" +
            "His pants have been stolen! Also, it looks as though he's been gouged and trampled by a horned beast. \n" +
            "Though his possessions are still intact, you're in no mind to find out what happened here...\n";
    private Button search = new Button("\t[Search] Find Loot. ");
    private Random rand = new Random();
    private int chance = rand.nextInt(100);

    public DeadAdventurer() {
        super();
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getStory() {
        return story;
    }

    public Image getImage() {
        return image;
    }

    public Button getButton1(Player player){
        search.setOnMouseClicked(event -> {
            if (chance <= 50) {
                setStory("You Found 30 Gold.");
                player.increaseGold(30);
            }
            else {
                setStory("You Found Nothing.");
            }
        });
        return search;
    }


   /* private Player player;
    private Scanner scanner = new Scanner(System.in);
    private Random rand = new Random();

    public DeadAdventurerEvent(Player player) {
        super("  You come across a dead adventurer on the floor.\n" +
                "\tHis pants have been stolen! Also, it looks as though he's been gouged and trampled by a horned beast. \n" +
                "\tThough his possessions are still intact, you're in no mind to find out what happened here...", "Dead Adventurer");
        setImagePath(new PathAssistent().toPath(this));
        this.player = player;
    }

    @Override
    public void startEvent() {
        ConsoleAssistent.clearScreen();
        int chance;
        String options;
        EventView.displayHead(getTitle(), getStory());
        chance = rand.nextInt(100);
        System.out.println("\t1. Search the body\n\t2. Leave\n\n");
        System.out.print("\tChoose an option: ");
        options = scanner.next();
        if (options.equals("1")) {
            if (chance >= 50) {
                System.out.println("\n\tYou found 30 Gold!");
                player.increaseGold(30);
            }
            else {
                System.out.println("\n\tHmm, couldn't find anything.");
                return;
            }
        }
        else if (options.equals("2")) {
            System.out.println("\n\tYou exit without a sound");
            return;
        }
        else {
            System.out.println("\n\tWrong input, please try agian...");
            startEvent();
        }
    }*/
}
