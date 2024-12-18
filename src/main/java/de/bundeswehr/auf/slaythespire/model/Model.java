package de.bundeswehr.auf.slaythespire.model;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.potion.structure.PotionCard;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Model {

    private static final String CARD = ".card.";
    private static final String DEFAULT_PACKAGE = "de.bundeswehr.auf.slaythespire.model";
    private static final String POTION = ".potion";
    private static final String RELIC = ".relic";

    private static final Map<String, Set<Class<? extends Card>>> cardCache = new HashMap<>();
    private static Set<Class<? extends PotionCard>> potionCache;
    private static Set<Class<? extends Relic>> relicCache;

    public static List<Class<? extends Card>> cards(String packageName) {
        Set<Class<? extends Card>> classes = loadCardClasses(packageName);
        return new ArrayList<>(classes);
    }

    @SuppressWarnings("unchecked")
    public static <C extends Card> C ofCards(String packageName, String name) {
        for (Class<? extends Card> cls : loadCardClasses(packageName)) {
            if (cls.getSimpleName().equals(name)) {
                try {
                    return (C) cls.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    LoggingAssistant.log(e, Color.RED);
                    LoggingAssistant.debug(e, Color.RED);
                }
            }
        }
        throw new IllegalArgumentException(packageName + " Card unknown: " + name);
    }

    @SuppressWarnings("unchecked")
    public static <P extends PotionCard> P ofPotions(String name) {
        for (Class<? extends PotionCard> cls : loadPotionClasses()) {
            if (cls.getSimpleName().equals(name)) {
                try {
                    return (P) cls.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    LoggingAssistant.log(e, Color.RED);
                    LoggingAssistant.debug(e, Color.RED);
                }
            }
        }
        throw new IllegalArgumentException("Potion unknown: " + name);
    }

    @SuppressWarnings("unchecked")
    public static <R extends Relic> R ofRelics(String name) {
        for (Class<? extends Relic> cls : loadRelicClasses()) {
            if (cls.getSimpleName().equals(name)) {
                try {
                    return (R) cls.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    LoggingAssistant.log(e, Color.RED);
                    LoggingAssistant.debug(e, Color.RED);
                }
            }
        }
        throw new IllegalArgumentException("Relic unknown: " + name);
    }

    public static List<Class<? extends PotionCard>> potions() {
        return new ArrayList<>(loadPotionClasses());
    }

    public static List<Class<? extends Relic>> relics() {
        return new ArrayList<>(loadRelicClasses());
    }

    private static Set<Class<? extends Card>> loadCardClasses(String key) {
        if (!cardCache.containsKey(key)) {
            Reflections reflections = new Reflections(DEFAULT_PACKAGE + CARD + key);
            cardCache.put(key, reflections.getSubTypesOf(Card.class));
        }
        return cardCache.get(key);
    }

    private static Set<Class<? extends PotionCard>> loadPotionClasses() {
        if (potionCache == null) {
            Reflections reflections = new Reflections(DEFAULT_PACKAGE + POTION);
            potionCache = reflections.getSubTypesOf(PotionCard.class);
        }
        return potionCache;
    }

    private static Set<Class<? extends Relic>> loadRelicClasses() {
        if (relicCache == null) {
            Reflections reflections = new Reflections(DEFAULT_PACKAGE + RELIC);
            relicCache = reflections.getSubTypesOf(Relic.class);
        }
        return relicCache;
    }

}
