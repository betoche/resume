package org.als.resume.entities;

import lombok.Getter;

@Getter
public enum Language {
    ENGLISH(1, "English", "English", "en"),
    SPANISH( 2, "Spanish", "Español", "es"),
    FRENCH( 3, "French", "Français", "fr");

    private final Integer id;
    private final String name;
    private final String nameInItsIdiom;
    private final String abbreviation;

    Language( Integer id, String name, String nameInItsIdiom, String abbreviation ) {
        this.id = id;
        this.name = name;
        this.nameInItsIdiom = nameInItsIdiom;
        this.abbreviation = abbreviation;
    }
}
