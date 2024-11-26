package view.gui.layouts.shop_layout;

import helper.Color;
import helper.ConsoleAssistent;
import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import view.gui.ShopView;


public class EntryLayout extends HBox {
    private String playerImagePath;
    private ShopView parentView;
    private VBox vMerchant;
    private Image merchantImg;
    private ImageView merchantImgView;


    public EntryLayout(ShopView parentView, String playerImagePath) {
        this.parentView = parentView;
        this.playerImagePath = playerImagePath;
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
        this.vMerchant = new VBox();
        setMerchantImageView();
        HBox.setHgrow(this.vMerchant, Priority.ALWAYS);
        this.vMerchant.setAlignment(Pos.CENTER);
        this.vMerchant.setTranslateY(150);
        this.vMerchant.setTranslateX(-100);

        this.merchantImgView.setOnMouseClicked(event -> {
            ConsoleAssistent.println(Color.YELLOW, "Clicked on Merchant");
            onMerchantClick();
            this.merchantImgView.setDisable(true);
        });

        GuiHelper.setHoverEffect(this.merchantImgView);

        this.vMerchant.getChildren().add(this.merchantImgView);
        getChildren().add(this.vMerchant);
    }

    private ImageView getPlayerImageView() {
        Image image = new Image(getClass().getResource(this.playerImagePath).toExternalForm());
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    private void setMerchantImageView() {
        this.merchantImg = new Image(getClass().getResource("/images/shop/MerchantWithProps.png").toExternalForm());
        this.merchantImgView = new ImageView(this.merchantImg);
    }

    private void onMerchantClick() {
        this.parentView.onMerchantClick();
    }
}
