package de.bundeswehr.auf.slaythespire.helper;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnyOf<T> extends DiagnosingMatcher<T> {

    private final Iterable<Matcher<? super T>> matchers;

    public static <T> Matcher<T> anyOf(Iterable<Matcher<? super T>> matchers) {
        return new AnyOf<>(matchers);
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<T>> anyOf(Matcher<? super T>... itemMatchers) {
        List<Matcher<? super Iterable<T>>> all = new ArrayList<>(itemMatchers.length);
        for (Matcher<? super T> elementMatcher : itemMatchers) {
            all.add(new AnyOf(elementMatcher));
        }
        return anyOf(all);
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<T>> anyOf(T... items) {
        List<Matcher<? super Iterable<T>>> all = new ArrayList<>(items.length);
        for (T item : items) {
            all.add(new Identical<>(item));
        }

        return anyOf(all);
    }

    public AnyOf(Matcher<? super T> matcher) {
        this(Collections.singletonList(matcher));
    }

    @SafeVarargs
    public AnyOf(Matcher<? super T>... matchers) {
        this(Arrays.asList(matchers));
    }

    public AnyOf(Iterable<Matcher<? super T>> matcherIterable) {
        List<Matcher<? super T>> matchers = new ArrayList<>();
        for (Matcher<? super T> matcher : matcherIterable) {
            matchers.add(matcher);
        }
        this.matchers = matchers;
    }

    @Override
    public void describeTo(Description description) {
        description.appendList("(", " " + "or" + " ", ")", matchers);
    }

    @Override
    public boolean matches(Object o, Description mismatch) {
        Iterable<T> iterable = (Iterable<T>) o;
        for (Matcher<? super T> matcher : matchers) {
            for (T actual : iterable) {
                if (matcher.matches(actual)) {
                    return true;
                }
            }
        }
        mismatch.appendList("(", " " + "or" + " ", ")", matchers);
        return false;
    }

}
