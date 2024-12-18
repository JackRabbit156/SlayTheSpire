package de.bundeswehr.auf.slaythespire.model.enemy.act_four;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.spiker.CutEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class SpikerEnemy extends Enemy {

    public SpikerEnemy() {
        super("Spiker", 42, 56);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new CutEnemyCard());

        setEnemyDeck(deck);
    }

}
