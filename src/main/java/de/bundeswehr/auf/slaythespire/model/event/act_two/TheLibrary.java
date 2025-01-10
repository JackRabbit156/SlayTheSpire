package de.bundeswehr.auf.slaythespire.model.event.act_two;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Der Spieler kann eine Karte hinzufügen, oder HP erhalten
 *
 * @author  Loeschner, Marijan
 */
public class TheLibrary extends Event {

    private final Button button1 = new Button("\t[Read] Choose one of 20 Cards.");
    private final Button button2 = new Button("\t[Sleep] Heal 1/3 of your max HP.");

    public TheLibrary(Player player) {
        super(player, "The Library", new Image("/images/event/act_two/library.jpg"),
            "\n\nYou come across an ornate building which appears abandoned.\n" +
                    "A plaque that has been torn free from a wall is on the floor. It reads, \"THE LIBRARY\".\n" +
                    "Inside, you find countless rows of scrolls, manuscripts, and books.\n" +
                    "You pick one and cozy yourself into a chair for some quiet time.\n");
    }

    @Override
    public Button getButton1() {
        button1.setOnAction(event -> {
            DeckFactory df = new DeckFactory(getPlayer(), 20);
            button1.setVisible(false);
            button2.setVisible(false);
            TilePane box = new TilePane();
            box.setBackground(Background.EMPTY);
            box.setMaxSize(1920, 1080);
            box.setAlignment(Pos.BOTTOM_CENTER);
            Popup cardPopup = new Popup();
            for(Card card : df.init(false)) {
                Image imageCard = new Image(getClass().getResource(card.getImagePath()).toExternalForm());
                ImageView imageView = new ImageView(imageCard);
                imageView.setFitHeight(250);
                imageView.setFitWidth(200);
                imageView.setOnMouseClicked(e -> {
                    getPlayer().addCardToDeck(card);
                    cardPopup.hide();
                });
                box.getChildren().addAll(imageView);
            }
            cardPopup.getContent().addAll(box);
            cardPopup.setAutoFix(true);
            cardPopup.show(button1.getScene().getWindow());
        });
        return button1;
    }

    @Override
    public Button getButton2() {
        button2.setOnAction(event -> {
            getPlayer().heal(getPlayer().getMaxHealth() / 3);
            button2.setVisible(false);
            button1.setVisible(false);

        });
        return button2;
    }

}
