package de.bundeswehr.auf.slaythespire.model.enemy.act_four;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.repulsor.BashRepulsorEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.repulsor.RepulseEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;


/**
 * @author L Frank Rieger
 */
public class RepulsorEnemy extends Enemy {

    public RepulsorEnemy() {
        super("Repulsor", 29, 35);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new BashRepulsorEnemyCard());
        deck.add(new RepulseEnemyCard());

        setEnemyDeck(deck);
    }

}
