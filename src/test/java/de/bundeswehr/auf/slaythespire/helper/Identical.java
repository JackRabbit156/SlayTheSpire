package de.bundeswehr.auf.slaythespire.helper;

import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Identical<T> extends DiagnosingMatcher<T> {

    private final T item;

    public Identical(T item) {
        this.item = item;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(item);
    }

    @Override
    public boolean matches(Object actual, Description mismatch) {
        return actual == item;
    }

}
