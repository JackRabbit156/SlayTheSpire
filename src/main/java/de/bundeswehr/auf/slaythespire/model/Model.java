package de.bundeswehr.auf.slaythespire.model;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class Model {

    private static final String CARD = ".card.";
    private static final String DEFAULT_PACKAGE = "de.bundeswehr.auf.slaythespire.model";
    private static final String POTION = ".potion";
    private static final String RELIC = ".relic";
    private static final String SETTINGS = ".settings";

    private static final Map<String, Set<Class<? extends Card>>> cardCache = new HashMap<>();
    private static Set<Class<? extends DifficultyLevel>> difficultyLevelCache;
    private static Set<Class<? extends Potion>> potionCache;
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
    public static <P extends DifficultyLevel> P ofDifficultyLevel(String name) {
        for (Class<? extends DifficultyLevel> cls : loadDifficultyLevelClasses()) {
            if (cls.getSimpleName().equals(name)) {
                try {
                    return (P) cls.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    LoggingAssistant.log(e, Color.RED);
                    LoggingAssistant.debug(e, Color.RED);
                }
            }
        }
        throw new IllegalArgumentException("Difficulty Level unknown: " + name);
    }

    @SuppressWarnings("unchecked")
    public static <P extends Potion> P ofPotions(String name) {
        for (Class<? extends Potion> cls : loadPotionClasses()) {
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

    public static List<Class<? extends Potion>> potions() {
        return new ArrayList<>(loadPotionClasses());
    }

    public static List<Class<? extends Relic>> relics() {
        return new ArrayList<>(loadRelicClasses());
    }

    private static Set<Class<? extends Card>> loadCardClasses(String key) {
        if (!cardCache.containsKey(key)) {
            Reflections reflections = new Reflections(DEFAULT_PACKAGE + CARD + key);
            cardCache.put(key, reflections.getSubTypesOf(Card.class).stream()
                    .filter(cls -> !Modifier.isAbstract(cls.getModifiers()))
                    .collect(Collectors.toSet()));
        }
        return cardCache.get(key);
    }

    private static Set<Class<? extends DifficultyLevel>> loadDifficultyLevelClasses() {
        if (difficultyLevelCache == null) {
            Reflections reflections = new Reflections(DEFAULT_PACKAGE + SETTINGS);
            difficultyLevelCache = reflections.getSubTypesOf(DifficultyLevel.class).stream()
                    .filter(cls -> !Modifier.isAbstract(cls.getModifiers()))
                    .collect(Collectors.toSet());
        }
        return difficultyLevelCache;
    }

    private static Set<Class<? extends Potion>> loadPotionClasses() {
        if (potionCache == null) {
            Reflections reflections = new Reflections(DEFAULT_PACKAGE + POTION);
            potionCache = reflections.getSubTypesOf(Potion.class).stream()
                    .filter(cls -> !Modifier.isAbstract(cls.getModifiers()))
                    .collect(Collectors.toSet());
        }
        return potionCache;
    }

    private static Set<Class<? extends Relic>> loadRelicClasses() {
        if (relicCache == null) {
            Reflections reflections = new Reflections(DEFAULT_PACKAGE + RELIC);
            relicCache = reflections.getSubTypesOf(Relic.class).stream()
                    .filter(cls -> !Modifier.isAbstract(cls.getModifiers()))
                    .collect(Collectors.toSet());
        }
        return relicCache;
    }

}
