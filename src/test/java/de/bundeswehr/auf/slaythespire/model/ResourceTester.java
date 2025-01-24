package de.bundeswehr.auf.slaythespire.model;

import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Effect;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.event.Event;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceTester {

    private static final String DEFAULT_PACKAGE = "de.bundeswehr.auf.slaythespire.model";

    @Test
    void card() {
        testImage("card", Card.class);
    }

    @Test
    void effect() {
        testImage("effect", Effect.class);
    }

    @Test
    void enemy() {
        testImage("enemy", Enemy.class);
    }

    @Test
    void enemy_card() {
        testImage("enemy_card", EnemyCard.class);
    }

    @Test
    void event() {
        testImage("event", Event.class);
    }

    @Test
    void player() {
        testImage("player", Player.class);
    }

    @Test
    void potion() {
        testImage("potion", Potion.class);
    }

    @Test
    void relic() {
        testImage("relic", Relic.class);
    }

    private <T> void testImage(String packageName, Class<T> type) {
        System.out.println("Checking images for " + packageName + "...");
        Set<Class<? extends T>> classes = new Reflections(DEFAULT_PACKAGE + "." + packageName)
                .getSubTypesOf(type)
                .stream()
                .filter(cls -> !Modifier.isAbstract(cls.getModifiers()))
                .collect(Collectors.toSet());
        for (Class<? extends T> oneClass : classes) {
            String name = toPath(oneClass);
            try {
                ResourceTester.class.getResource(name).toExternalForm();
            } catch (NullPointerException e) {
                throw new AssertionError("No image for " + oneClass.getSimpleName() + ": " + name);
            }
        }
        System.out.println(classes.size() + " checked");
    }

    private String toPath(Class<?> aClass) {
        String path = "/" + aClass.getPackage().getName() + "." + aClass.getSimpleName();
        path = path.replace(".", "/");
        path = path.replaceFirst("de/bundeswehr/auf/slaythespire/model", "images");
        path += ".png";

        return path;
    }

}
