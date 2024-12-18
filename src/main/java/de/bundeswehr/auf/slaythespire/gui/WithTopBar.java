package de.bundeswehr.auf.slaythespire.gui;

public interface WithTopBar {

    void discard();

    void onFullScreen();

    default void onLibrary() {
        // do nothing by default
    }

    default void onMap() {
        // do nothing by default
    }

    default void onSettings() {
        // do nothing by default
    }

    default boolean showLibrary() {
        return true;
    }

    default boolean showMap() {
        return false;
    }

    default boolean showSettings() {
        return false;
    }

}
