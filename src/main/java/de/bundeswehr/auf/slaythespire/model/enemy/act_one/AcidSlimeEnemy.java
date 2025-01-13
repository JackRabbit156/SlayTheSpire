package de.bundeswehr.auf.slaythespire.model.enemy.act_one;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.acid_slime.CorrosiveSpitEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.acid_slime.LickEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.acid_slime.TackleEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert den Gegner "Acid Slime" im ersten Akt des Spiels.
 * Der Acid Slime hat spezifische Eigenschaften und Fähigkeiten,
 * die im Kampf verwendet werden können.
 *
 * @author Warawa Alexander
 * @author OF Daniel Willig
 */
public class AcidSlimeEnemy extends Enemy {

    public AcidSlimeEnemy() {
        super("Acid Slime (L)", 65, 69);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new CorrosiveSpitEnemyCard());
        deck.add(new LickEnemyCard());
        deck.add(new TackleEnemyCard());

        setEnemyDeck(deck);
    }

}