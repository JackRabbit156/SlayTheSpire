package de.bundeswehr.auf.slaythespire.model.enemy.act_four;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.exploder.ExplodeEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.exploder.SlamExploderEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;


/**
 * @author L Frank Rieger
 */
public class ExploderEnemy extends Enemy {

    public ExploderEnemy() {
        super("Exploder", 30, 30);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new SlamExploderEnemyCard());
        deck.add(new ExplodeEnemyCard());

        setEnemyDeck(deck);
    }

}
