package view.gui.layouts.treasure_layout;

import helper.Color;
import helper.ConsoleAssistent;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import view.gui.TreasureView;

public class EntryLayout extends HBox {
    private String playerImagePath;
    private TreasureView parentView;
    private VBox vTreasure;
    private Image treasureImg;
    private ImageView treasureImgView;

    public EntryLayout(TreasureView parentView, String playerImagePath) {
        this.parentView = parentView;
        this.playerImagePath = playerImagePath;
        this.setTreasureImageView(false);
        init();
    }

    public void init() {
        initLeft();
        initRight();
    }

    private void initLeft() {
        VBox vPlayer = new VBox();
        HBox.setHgrow(vPlayer, Priority.ALWAYS);
        vPlayer.setAlignment(Pos.CENTER);
        vPlayer.setTranslateY(150);
        vPlayer.setTranslateX(150);
        vPlayer.getChildren().add(getPlayerImageView());
        getChildren().add(vPlayer);
    }

    private void initRight() {
        this.vTreasure = new VBox();
        HBox.setHgrow(this.vTreasure, Priority.ALWAYS);
        this.vTreasure.setAlignment(Pos.CENTER);
        this.vTreasure.setTranslateY(50);
        this.vTreasure.setTranslateX(-150);

        this.treasureImgView.setOnMouseClicked(event -> {
            ConsoleAssistent.println(Color.YELLOW, "Clicked on Treasure");
            setTreasureImageView(true);
            onTreasureClick();
            this.treasureImgView.setDisable(true);
        });
        this.vTreasure.getChildren().add(this.treasureImgView);
        getChildren().add(this.vTreasure);
    }

    private ImageView getPlayerImageView() {
        Image image = new Image(getClass().getResource(this.playerImagePath).toExternalForm());
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    private void setTreasureImageView(boolean open) {
        if (!open) {
            this.treasureImg = new Image(getClass().getResource("/images/treasure/treasure.png").toExternalForm());
        } else {
            ConsoleAssistent.println(Color.YELLOW, "Treasure Open");
            this.treasureImg = new Image(getClass().getResource("/images/treasure/treasureOpen.png").toExternalForm());
        }
        this.treasureImgView = new ImageView(this.treasureImg);
    }

    private void onTreasureClick(){
        getChildren().remove(0,2);
        init();
        this.parentView.onTreasureClick();
    }
}
