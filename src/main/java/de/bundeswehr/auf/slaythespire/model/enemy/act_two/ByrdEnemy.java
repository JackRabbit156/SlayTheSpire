package de.bundeswehr.auf.slaythespire.model.enemy.act_two;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.byrd.CawEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.byrd.HeadbuttEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.byrd.PeckEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.byrd.SwoopEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert den Gegner "Byrd" im zweiten Akt des Spiels.
 * Byrd hat spezifische Angriffsfähigkeiten, die bei jedem
 * Angriff zufällig ausgewählt werden.
 *
 * @author Warawa Alexander
 * @author OF Daniel Willig
 */
public class ByrdEnemy extends Enemy {

    public ByrdEnemy() {
        super("Byrd", 25, 31);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new CawEnemyCard());
        deck.add(new HeadbuttEnemyCard());
        deck.add(new PeckEnemyCard());
        deck.add(new SwoopEnemyCard());

        setEnemyDeck(deck);
    }

}