import models.cards.BashCard;
import models.cards.DefendCard;
import models.cards.StrikeCard;
import models.cards.card_structure.Card;
import models.player.Ironclad;
import view.CampfireView;
import view.ShopView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Ironclad player = new Ironclad();

//        List<Enemy> enemies = new ArrayList<Enemy>();
//
//        enemies.add(new AcidSlime());
//        enemies.add(new Cultist());
//        enemies.add(new SpikeSlime());
//
//        BattleViewController controller = new BattleViewController(player, enemies);
//        controller.startBattle();

        List<Card> shopCards = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            shopCards.add(new StrikeCard());

        for (int i = 0; i < 4; i++)
            shopCards.add(new DefendCard());

        shopCards.add(new BashCard());

        CampfireView camp = new CampfireView();
        ShopView shop = new ShopView();
        shop.display(player, shopCards);
//        camp.display(player);
    }


}