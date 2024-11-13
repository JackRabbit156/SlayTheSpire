package view.gui.layouts.battle_view_layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import models.player.player_structure.Player;

public class PlayerLayout extends VBox {
    private Player player;

    private String imagePath;
    private HealthBarLayout healthBar;
    private MovingAnimation animation;

    //private ProgressBar healthBar;
    public PlayerLayout(Player player){
        this.player = player;

        this.imagePath = "/images/Ironclad.png";
        this.getChildren().add(image());
        healthBar = new HealthBarLayout(100);
        this.getChildren().add(healthBar);

        this.setPadding(new Insets(300, 0, 0, 450));
        this.alignmentProperty().set(Pos.CENTER_LEFT);

        updatePlayer();

        animation = new MovingAnimation(this);
        animation.start();
    }

    public void updatePlayer(){
        healthBar.setHealthText(player.getCurrentHealth(), player.getMaxHealth());
    }

    private ImageView image() {
        Image figureImage = new Image(getClass().getResource(imagePath).toExternalForm());
        ImageView imageViewFigure = new ImageView(figureImage);

        imageViewFigure.setFitWidth(350); // Breite in Pixel
        imageViewFigure.setFitHeight(350); // Höhe in Pixel
        imageViewFigure.setPreserveRatio(true);
        //handBox = new Pane( imageViewIronclad);
        imageViewFigure.setStyle("-fx-background-color: #926099;");

        return imageViewFigure;
    }
}